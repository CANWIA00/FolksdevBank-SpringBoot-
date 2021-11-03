package com.folksdev.bank.folksdevbank.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MoneyTransferRequest {
    private String formId;
    private String toId;
    private Double amount;
}
