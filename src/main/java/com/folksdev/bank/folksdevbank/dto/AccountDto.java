package com.folksdev.bank.folksdevbank.dto;

import com.folksdev.bank.folksdevbank.model.City;
import com.folksdev.bank.folksdevbank.model.Currency;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class AccountDto implements Serializable {

    private String id;
    private String customerId;
    private Double balance;
    private Currency currency;
}
