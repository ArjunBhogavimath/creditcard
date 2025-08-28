package com.example.creditcard.controller;


import com.example.creditcard.dto.CreditCardDto;
import com.example.creditcard.dto.LimitInfoDto;
import com.example.creditcard.model.CreditCard;
import com.example.creditcard.service.CreditCardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cards")
public class CreditCardController {

    @Autowired
    private CreditCardService creditCardService;

    @PostMapping
    public ResponseEntity<CreditCard> addCard(@Valid @RequestBody CreditCardDto cardDto){
        return ResponseEntity.ok(creditCardService.addCard(cardDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditCard> getCardById(@PathVariable Long id){
        return ResponseEntity.ok(creditCardService.getCardById(id));
    }

    @GetMapping
    public ResponseEntity<Page<CreditCard>> getAllCards(Pageable pageable){
        return ResponseEntity.ok(creditCardService.getAllCards(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCard(@PathVariable Long id){
        creditCardService.deleteCard(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/limit")
    public ResponseEntity<LimitInfoDto> getCardLimit(@PathVariable Long id){
        return ResponseEntity.ok(creditCardService.getCardLimit(id));
    }

}
