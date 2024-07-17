package org.bank.accountserviceaxon.commonapi.events;

import lombok.Getter;
import org.bank.accountserviceaxon.commonapi.enums.AccountStatus;

public class AccountDebitedEvent extends BaseEvent<String> {

    @Getter
    private String currency;
    @Getter private double amount;

    public AccountDebitedEvent(String accountId, String currency, double amount) {
        super(accountId);
        this.currency = currency;
        this.amount = amount;
    }
}
