package com.folksdev.bank.folksdevbank.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatAccountRequest extends BaseAccountRequest{
    @NotBlank(message = "Account id must not be empty")
    private String id;
}
