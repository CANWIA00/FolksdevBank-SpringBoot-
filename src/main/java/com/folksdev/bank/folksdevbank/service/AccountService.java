package com.folksdev.bank.folksdevbank.service;

import com.folksdev.bank.folksdevbank.dto.AccountDto;
import com.folksdev.bank.folksdevbank.dto.converter.AccountDtoConverter;
import com.folksdev.bank.folksdevbank.dto.request.CreatAccountRequest;
import com.folksdev.bank.folksdevbank.dto.request.MoneyTransferRequest;
import com.folksdev.bank.folksdevbank.dto.request.UpdateAccountRequest;
import com.folksdev.bank.folksdevbank.exception.CustomerNotFoundException;
import com.folksdev.bank.folksdevbank.model.Account;
import com.folksdev.bank.folksdevbank.model.Customer;
import com.folksdev.bank.folksdevbank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerService customerService;
    private final AccountDtoConverter accountDtoConverter;

    private final KafkaTemplate<String, String> kafkaTemplate;


    @Value("${sample.rabbitmq.routingKey}")
    String routingKey;

    @Value("${sample.rabbitmq.queue}")
    String queueName;

    public AccountService(AccountDtoConverter accountDtoConverter, AccountRepository accountRepository, CustomerService customerService, KafkaTemplate<String, String> kafkaTemplate) {
        this.accountDtoConverter = accountDtoConverter;
        this.accountRepository = accountRepository;
        this.customerService = customerService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @CachePut(value = "accounts" , key = "#id")
    public AccountDto createAccount(CreatAccountRequest creatAccountRequest){
        Customer customer = customerService.getCustomerById(creatAccountRequest.getCustomerId());

        if(customer.getId() == null || customer.getId().trim().equals("")){
            throw new CustomerNotFoundException("Customer Not Found!!");
        }

        Account account = Account.builder()
                .id(creatAccountRequest.getId())
                .balance(creatAccountRequest.getBalance())
                .currency(creatAccountRequest.getCurrency())
                .customerId(creatAccountRequest.getCustomerId())
                .city(creatAccountRequest.getCity())
                .build();

        return accountDtoConverter.convert(accountRepository.save(account));
    }


    @CacheEvict(value = "accounts", allEntries = true)
    public AccountDto updateAccount(String id, UpdateAccountRequest request){
        Customer customer = customerService.getCustomerById(request.getCustomerId());

        if(customer.getId().equals("")||customer.getId()==null){
            return AccountDto.builder().build();
        }

        Optional<Account>accountOptional=accountRepository.findById(id);
        accountOptional.ifPresent(account -> {
            account.setBalance(request.getBalance());
            account.setCity(request.getCity());
            account.setCurrency(request.getCurrency());
            account.setCustomerId(request.getCustomerId());

            accountRepository.save(account);
        });

        return accountOptional.map(accountDtoConverter::convert).orElse(new AccountDto());

    }

    @Cacheable(value = "accounts")
    public List<AccountDto> getAllAccountsDto() {
        List<Account>accountList = accountRepository.findAll();
        return accountList.stream().map(accountDtoConverter::convert).collect(Collectors.toList());
    }

    public AccountDto getAccountById(String id){
        return accountRepository.findById(id)
                .map(accountDtoConverter::convert)
                .orElse(new AccountDto());
    }

    @CacheEvict(value = "accounts", allEntries = true)
    public void deleteAccount(String id) {
        accountRepository.deleteById(id);
    }

    public AccountDto withdrawMoney(String id, Double amount) {
        Optional<Account>optionalAccount = accountRepository.findById(id);

        optionalAccount.ifPresent(account -> {
            if (account.getBalance() > amount){
                account.setBalance(account.getBalance() - amount);
                accountRepository.save(account);
            }else {
                System.out.println("Insufficient funds -> accountId: " + id +"balance: " +account.getBalance() + "amount: " + amount);
            }
        });
        return optionalAccount.map(accountDtoConverter::convert).orElse(new AccountDto());
    }

    public AccountDto addMoney(String id, Double amount) {
        Optional<Account>optionalAccount = accountRepository.findById(id);

        optionalAccount.ifPresent(account -> {
            account.setBalance(account.getBalance() + amount);

            accountRepository.save(account);
        });

        return optionalAccount.map(accountDtoConverter::convert).orElse(new AccountDto());
    }

    public void transferMoney(MoneyTransferRequest transferRequest){

    }














}
