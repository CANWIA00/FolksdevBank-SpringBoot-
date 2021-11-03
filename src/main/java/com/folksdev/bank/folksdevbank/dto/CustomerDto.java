package com.folksdev.bank.folksdevbank.dto;

import com.folksdev.bank.folksdevbank.model.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto implements Serializable {

    private String id;
    private String name;
    private Integer dateOfBirth;
    private CityDto city;
    private Address address;
}
