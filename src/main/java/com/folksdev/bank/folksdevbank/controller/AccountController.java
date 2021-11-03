package com.folksdev.bank.folksdevbank.controller;

import com.folksdev.bank.folksdevbank.dto.AccountDto;
import com.folksdev.bank.folksdevbank.dto.request.CreatAccountRequest;
import com.folksdev.bank.folksdevbank.dto.request.MoneyTransferRequest;
import com.folksdev.bank.folksdevbank.dto.request.UpdateAccountRequest;
import com.folksdev.bank.folksdevbank.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/v1/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    public ResponseEntity<AccountDto> getAccountById(@PathVariable String id){
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    public ResponseEntity<Object> createAccount(@Valid@RequestBody CreatAccountRequest creatAccountRequest){
        return ResponseEntity.ok(accountService.createAccount(creatAccountRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable String id, @RequestBody UpdateAccountRequest updateAccountRequest){
        return ResponseEntity.ok(accountService.updateAccount(id,updateAccountRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/withdraw/{id}/{amount}")
    public ResponseEntity<AccountDto> withdrawMoney(@PathVariable String id, @PathVariable Double amount){
        return ResponseEntity.ok(accountService.withdrawMoney(id,amount));
    }

    @PutMapping("/add/{id}/{amount}")
    public ResponseEntity<AccountDto> addMoney(@PathVariable String id, Double amount){
        return ResponseEntity.ok(accountService.addMoney(id,amount));
    }

    @PutMapping("/transfer")
    public ResponseEntity<String> transferMoney(@RequestBody MoneyTransferRequest transferRequest){
        accountService.transferMoney(transferRequest);
        return ResponseEntity.ok("İşleminiz başarıyla alınmıştır!");
    }
}

