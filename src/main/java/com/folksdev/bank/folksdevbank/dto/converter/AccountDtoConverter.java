package com.folksdev.bank.folksdevbank.dto.converter;

import com.folksdev.bank.folksdevbank.dto.AccountDto;
import com.folksdev.bank.folksdevbank.model.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountDtoConverter {

    public AccountDto convert(Account account){
        return AccountDto.builder()
                .id(account.getId())
                .balance(account.getBalance())
                .currency(account.getCurrency())
                .customerId(account.getCustomerId())
                .build();
    }
}