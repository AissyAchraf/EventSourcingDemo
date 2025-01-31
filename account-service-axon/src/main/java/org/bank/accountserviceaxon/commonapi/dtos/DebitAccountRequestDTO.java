package org.bank.accountserviceaxon.commonapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DebitAccountRequestDTO {
    private String accountId;
    private String currency;
    private Double amount;
}
