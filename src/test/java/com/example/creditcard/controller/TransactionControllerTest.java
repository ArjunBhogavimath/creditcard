package com.example.creditcard.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.creditcard.dto.TransactionDto;
import com.example.creditcard.model.Transaction;
import com.example.creditcard.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TransactionController.class)
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "admin", password = "admin123", roles = "ADMIN")
    void makeTransaction_ShouldReturnTransaction() throws Exception {
        TransactionDto dto = new TransactionDto();
        dto.setCardId(1L);
        dto.setAmount(100.0);
        dto.setMerchantName("Amazon");

        Transaction tx = new Transaction();
        tx.setId(1L);
        tx.setAmount(100.0);
        tx.setMerchantName("Amazon");

        Mockito.when(transactionService.makeTransaction(Mockito.any(TransactionDto.class))).thenReturn(tx);

        mockMvc.perform(post("/api/v1/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.merchantName").value("Amazon"));
    }
}
