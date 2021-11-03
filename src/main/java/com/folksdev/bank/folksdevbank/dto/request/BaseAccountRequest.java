package com.folksdev.bank.folksdevbank.dto.request;

import com.folksdev.bank.folksdevbank.model.City;
import com.folksdev.bank.folksdevbank.model.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseAccountRequest {
    @NotBlank(message="Customer id must not be null")
    private String customerId;

    @NotNull
    @Min(0)
    private Double balance;

    @NotBlank(message = "Currency must not be null")
    private Currency currency;

    @NotNull(message = "City must not be null")
    private City city;
}
