package com.folksdev.bank.folksdevbank.repository;

import com.folksdev.bank.folksdevbank.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,String> {
}
