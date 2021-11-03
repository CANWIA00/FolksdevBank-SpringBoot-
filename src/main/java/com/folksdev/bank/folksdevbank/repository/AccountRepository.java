package com.folksdev.bank.folksdevbank.repository;

import com.folksdev.bank.folksdevbank.model.Account;
import com.folksdev.bank.folksdevbank.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface AccountRepository extends JpaRepository<Account,String> {

    List<Account> findAllByBalanceGreaterThan(BigDecimal balance);
    List<Account>findAllByCurrencyAndAndBalanceLessThan(Currency currency, BigDecimal balance);

}
