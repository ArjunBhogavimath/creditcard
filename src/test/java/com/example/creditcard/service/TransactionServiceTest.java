package com.example.creditcard.service;

import com.example.creditcard.dto.TransactionDto;
import com.example.creditcard.exception.LimitExceededException;
import com.example.creditcard.exception.ResourceNotFoundException;
import com.example.creditcard.model.CreditCard;
import com.example.creditcard.model.Transaction;
import com.example.creditcard.repository.CreditCardRepository;
import com.example.creditcard.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private CreditCardRepository creditCardRepository;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void makeTransaction_ShouldSucceed_WhenLimitAvailable() {
        CreditCard card = new CreditCard();
        card.setId(1L);
        card.setTotalLimit(1000.0);
        card.setUsedLimit(200.0);

        TransactionDto dto = new TransactionDto();
        dto.setCardId(1L);
        dto.setAmount(100.0);
        dto.setMerchantName("Amazon");

        when(creditCardRepository.findById(1L)).thenReturn(Optional.of(card));
        when(transactionRepository.save(any(Transaction.class))).thenAnswer(i -> i.getArgument(0));

        Transaction tx = transactionService.makeTransaction(dto);

        assertEquals(100.0, tx.getAmount());
        assertEquals("Amazon", tx.getMerchantName());
        assertEquals(300.0, card.getUsedLimit());
    }

    @Test
    void makeTransaction_ShouldThrow_WhenLimitExceeded() {
        CreditCard card = new CreditCard();
        card.setId(1L);
        card.setTotalLimit(500.0);
        card.setUsedLimit(400.0);

        TransactionDto dto = new TransactionDto();
        dto.setCardId(1L);
        dto.setAmount(200.0);
        dto.setMerchantName("Flipkart");

        when(creditCardRepository.findById(1L)).thenReturn(Optional.of(card));

        assertThrows(LimitExceededException.class, () -> transactionService.makeTransaction(dto));
    }

    @Test
    void getTransactionsByCard_ShouldReturnPage() {
        CreditCard card = new CreditCard();
        card.setId(1L);

        Transaction tx = new Transaction();
        tx.setId(1L);
        tx.setAmount(100.0);

        when(creditCardRepository.existsById(1L)).thenReturn(true);
        when(transactionRepository.findByCreditCardId(eq(1L), any(PageRequest.class)))
                .thenReturn(new PageImpl<>(java.util.List.of(tx)));

        Page<Transaction> page = transactionService.getTransactionByCard(1L, PageRequest.of(0, 5));

        assertEquals(1, page.getTotalElements());
    }

    @Test
    void getTransactionsByCard_ShouldThrow_WhenCardNotFound() {
        when(creditCardRepository.existsById(1L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class,
                () -> transactionService.getTransactionByCard(1L, PageRequest.of(0, 5)));
    }
}
