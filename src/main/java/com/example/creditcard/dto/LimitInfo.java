package com.example.creditcard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LimitInfo {

    private Double totalLimit;

    private Double usedLimit;

    private Double availableLimit;
}
