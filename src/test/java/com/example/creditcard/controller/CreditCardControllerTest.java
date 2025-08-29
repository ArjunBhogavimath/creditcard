package com.example.creditcard.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.creditcard.dto.CreditCardDto;
import com.example.creditcard.model.CreditCard;
import com.example.creditcard.service.CreditCardService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CreditCardController.class)
@Import(CreditCardControllerTest.TestConfig.class) // ✅ ensures mock is loaded
class CreditCardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CreditCardService creditCardService;

    @Autowired
    private ObjectMapper objectMapper;

    @TestConfiguration
    static class TestConfig {
        @Bean
        CreditCardService creditCardService() {
            return Mockito.mock(CreditCardService.class);
        }
    }

    @Test
    @WithMockUser(username = "admin", password = "admin123", roles = "ADMIN")
    void addCard_ShouldReturnCreatedCard() throws Exception {
        // Arrange
        CreditCardDto dto = new CreditCardDto();
        dto.setCardHolderName("John Doe");
        dto.setCardNumber("1234567812345678");
        dto.setExpiryDate("12/29");
        dto.setCvv("123");
        dto.setTotalLimit(5000.0);

        CreditCard card = new CreditCard();
        card.setId(1L);
        card.setCardHolderName("John Doe");

        Mockito.when(creditCardService.addCard(Mockito.any(CreditCardDto.class)))
                .thenReturn(card);

        // Act & Assert
        mockMvc.perform(post("/api/v1/cards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cardHolderName").value("John Doe"));
    }
}
