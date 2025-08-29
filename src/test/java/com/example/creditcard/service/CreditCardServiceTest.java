package com.example.creditcard.service;

import com.example.creditcard.dto.CreditCardDto;
import com.example.creditcard.dto.LimitInfoDto;
import com.example.creditcard.exception.ResourceNotFoundException;
import com.example.creditcard.model.CreditCard;
import com.example.creditcard.repository.CreditCardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CreditCardServiceTest {

    @Mock
    private CreditCardRepository creditCardRepository;

    @InjectMocks
    private CreditCardService creditCardService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void addCard_ShouldSaveCard(){
        CreditCardDto dto = new CreditCardDto();
        dto.setCardHolderName("John Doe");
        dto.setCardNumber("1234567812345678");
        dto.setCvv("123");
        dto.setExpiryDate("12/29");
        dto.setTotalLimit(5000.0);

        CreditCard card = new CreditCard();
        card.setId(1L);
        card.setCardHolderName(dto.getCardHolderName());
        when(creditCardRepository.save(any(CreditCard.class))).thenReturn(card);

        CreditCard saved = creditCardService.addCard(dto);

        assertNotNull(saved);
        assertEquals("John Doe", saved.getCardHolderName());
    }

    @Test
    void getCardById_ShouldReturnCard() {
        CreditCard card = new CreditCard();
        card.setId(1L);
        when(creditCardRepository.findById(1L)).thenReturn(Optional.of(card));

        CreditCard found = creditCardService.getCardById(1L);

        assertEquals(1L, found.getId());
    }

    @Test
    void getCardById_ShouldThrowException_WhenNotFound() {
        when(creditCardRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> creditCardService.getCardById(1L));
    }

    @Test
    void getAllCards_ShouldReturnPaginated() {
        CreditCard card = new CreditCard();
        card.setId(1L);
        Page<CreditCard> page = new PageImpl<>(List.of(card));
        when(creditCardRepository.findAll(any(PageRequest.class))).thenReturn(page);

        Page<CreditCard> result = creditCardService.getAllCards(PageRequest.of(0, 10));

        assertEquals(1, result.getTotalElements());
    }

    @Test
    void getCardLimitInfo_ShouldReturnLimits() {
        CreditCard card = new CreditCard();
        card.setId(1L);
        card.setTotalLimit(1000.0);
        card.setUsedLimit(200.0);

        when(creditCardRepository.findById(1L)).thenReturn(Optional.of(card));

        LimitInfoDto info = creditCardService.getCardLimit(1L);

        assertEquals(1000.0, info.getTotalLimit());
        assertEquals(200.0, info.getUsedLimit());
        assertEquals(800.0, info.getAvailableLimit());
    }
}
