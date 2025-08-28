package com.example.creditcard.controller;

import com.example.creditcard.dto.TransactionDto;
import com.example.creditcard.model.Transaction;
import com.example.creditcard.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> makeTransaction(@Valid @RequestBody TransactionDto transactionDto){
        return ResponseEntity.ok(transactionService.makeTransaction(transactionDto));
    }

    @GetMapping("/card/{cardId}")
    public ResponseEntity<Page<Transaction>> getTransactionsByCard(@PathVariable Long cardId, Pageable pageable){
        return ResponseEntity.ok(transactionService.getTransactinByCard(cardId,pageable));
    }
}
