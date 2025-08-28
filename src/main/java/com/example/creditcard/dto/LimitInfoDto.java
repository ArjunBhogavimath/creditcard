package com.example.creditcard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LimitInfoDto {

    private Double totalLimit;

    private Double usedLimit;

    private Double availableLimit;
}
