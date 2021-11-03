package com.folksdev.bank.folksdevbank.repository;

import com.folksdev.bank.folksdevbank.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,String > {
}
