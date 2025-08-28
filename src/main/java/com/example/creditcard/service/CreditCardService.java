package com.example.creditcard.service;

import com.example.creditcard.dto.CreditCardDto;
import com.example.creditcard.model.CreditCard;
import com.example.creditcard.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    public CreditCard addCard(CreditCardDto request){
        CreditCard card = new CreditCard();
        card.setCardHolderName(request.getCardHolderName());
        card.setCardNumber(request.getCardNumber());
        card.setExpiryDate(request.getExpiryDate());
        card.setCvv(request.getCvv());
        card.setTotalLimit(request.getTotalLimit());
        card.setUsedLimit(0.0); //we dont have this in the beginning so make it as zero
        return creditCardRepository.save(card);
    }
}
