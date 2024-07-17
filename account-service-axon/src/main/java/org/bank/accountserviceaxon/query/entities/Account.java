package org.bank.accountserviceaxon.query.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bank.accountserviceaxon.commonapi.enums.AccountStatus;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {
    @Id
    private String id;
    private Instant createdAt;
    private double balance;
    private AccountStatus status;
    private String currency;
    @OneToMany(mappedBy = "account")
    private List<AccountTransaction> transactions;
}
