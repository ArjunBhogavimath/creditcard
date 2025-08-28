package com.example.creditcard.controller;


import com.example.creditcard.dto.CreditCardDto;
import com.example.creditcard.model.CreditCard;
import com.example.creditcard.service.CreditCardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cards")
public class CreditCardController {

    @Autowired
    private CreditCardService creditCardService;

    @PostMapping
    public ResponseEntity<CreditCard> addCard(@Valid @RequestBody CreditCardDto cardDto){
        return ResponseEntity.ok(creditCardService.addCard(cardDto));
    }
}
