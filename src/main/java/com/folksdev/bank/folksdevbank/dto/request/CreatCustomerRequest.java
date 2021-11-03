package com.folksdev.bank.folksdevbank.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatCustomerRequest extends BaseCustomerRequest{
  private String id;
}
