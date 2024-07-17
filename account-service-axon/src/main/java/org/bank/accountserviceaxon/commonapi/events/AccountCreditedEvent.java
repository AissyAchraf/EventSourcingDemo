package org.bank.accountserviceaxon.commonapi.events;

import lombok.Getter;

public class AccountCreditedEvent extends BaseEvent<String> {

    @Getter
    private String currency;
    @Getter private double amount;

    public AccountCreditedEvent(String accountId, String currency, double amount) {
        super(accountId);
        this.currency = currency;
        this.amount = amount;
    }
}
