package org.bank.accountserviceaxon.commonapi.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateAccountRequestDTO {
    private String currency;
    private Double initialBalance;
}
