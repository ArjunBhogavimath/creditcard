package com.example.creditcard.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CreditCardDto {

    @NotBlank
    private String cardHolderName;

    @Pattern(regexp = "\\d{16}", message = "Card number must be 16 digits")
    private String cardNumber;

    @Pattern(regexp = "(0[1-9]|1[0-2])/\\d{2}", message = "Expiry date must be MM/yy")
    private String expiryDate;

    @Pattern(regexp = "\\d{3}", message = "CVV must be 3 digits")
    private String cvv;

    @NotNull
    @Positive
    private Double totalLimit;
}
