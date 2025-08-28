package com.example.creditcard.controller;

import com.example.creditcard.dto.TransactionDto;
import com.example.creditcard.model.Transaction;
import com.example.creditcard.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> makeTransaction(@Valid @RequestBody TransactionDto transactionDto){
        return ResponseEntity.ok()
    }
}
