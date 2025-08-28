package com.example.creditcard.service;

import com.example.creditcard.dto.TransactionDto;
import com.example.creditcard.exception.ResourceNotFoundException;
import com.example.creditcard.model.CreditCard;
import com.example.creditcard.model.Transaction;
import com.example.creditcard.exception.LimitExceededException;
import com.example.creditcard.repository.CreditCardRepository;
import com.example.creditcard.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CreditCardRepository creditCardRepository;

    public Transaction makeTransaction(TransactionDto transactionDto) {
        CreditCard card = creditCardRepository.findById(transactionDto.getCardId())
                .orElseThrow(() -> new ResourceNotFoundException("Credit Card not found with id: " + transactionDto.getCardId()));

        if(transactionDto.getAmount() > card.getAvailableLimit()){
            throw new LimitExceededException("Transaction amount exceeds available limit.");
        }

        Transaction transaction = new Transaction();

        transaction.setAmount(transactionDto.getAmount());
        transaction.setMerchantName(transactionDto.getMerchantName());
        transaction.setCreditCard(card);

        card.setUsedLimit(card.getUsedLimit() + transactionDto.getAmount());
        creditCardRepository.save(card);

        return transactionRepository.save(transaction);
    }
}
