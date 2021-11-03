package com.folksdev.bank.folksdevbank.dto.converter;

import com.folksdev.bank.folksdevbank.dto.CityDto;
import com.folksdev.bank.folksdevbank.dto.CustomerDto;
import com.folksdev.bank.folksdevbank.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerDtoConverter {
    public CustomerDto convert(Customer customer){
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setName(customer.getName());
        customerDto.setDateOfBirth(customer.getDateOfBirth());
        customerDto.setCity(CityDto.valueOf(customer.getCity().name()));
        customerDto.setAddress(customer.getAddress());
        return customerDto;
    }
}
