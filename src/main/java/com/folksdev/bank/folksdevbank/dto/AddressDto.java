package com.folksdev.bank.folksdevbank.dto;

import com.folksdev.bank.folksdevbank.model.City;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class AddressDto {
    private String id;
    private City city;
    private String postCode;
}
