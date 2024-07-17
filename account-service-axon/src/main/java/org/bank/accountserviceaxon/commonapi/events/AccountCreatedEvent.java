package org.bank.accountserviceaxon.commonapi.events;

import lombok.Getter;
import org.bank.accountserviceaxon.commonapi.enums.AccountStatus;

public class AccountCreatedEvent extends BaseEvent<String> {

    @Getter private String currency;
    @Getter private double balance;
    @Getter private AccountStatus status;

    public AccountCreatedEvent(String accountId, String currency, double balance, AccountStatus status) {
        super(accountId);
        this.currency = currency;
        this.balance = balance;
        this.status = status;
    }
}
