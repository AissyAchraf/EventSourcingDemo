package org.bank.accountserviceaxon.commands.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.bank.accountserviceaxon.commonapi.commands.CreateAccountCommand;
import org.bank.accountserviceaxon.commonapi.commands.CreditAccountCommand;
import org.bank.accountserviceaxon.commonapi.commands.DebitAccountCommand;
import org.bank.accountserviceaxon.commonapi.enums.AccountStatus;
import org.bank.accountserviceaxon.commonapi.events.AccountCreatedEvent;
import org.bank.accountserviceaxon.commonapi.events.AccountCreditedEvent;
import org.bank.accountserviceaxon.commonapi.events.AccountDebitedEvent;
import org.bank.accountserviceaxon.commonapi.exceptions.NegativeInitialBalanceException;

@Aggregate
public class AccountAggregate {

    @AggregateIdentifier
    private String accountId;
    private String currency;
    private double balance;
    private AccountStatus status;

    public AccountAggregate() {
        // required by AXON
    }

    @CommandHandler
    public AccountAggregate(CreateAccountCommand command) {
        if(command.getInitialBalance() < 0) {
            throw new NegativeInitialBalanceException("Negative Balance");
        }
        AggregateLifecycle.apply(new AccountCreatedEvent(
                command.getId(),
                command.getCurrency(),
                command.getInitialBalance(),
                AccountStatus.CREATED
        ));
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent event) {
        this.accountId = event.getId();
        this.balance = event.getBalance();
        this.currency = event.getCurrency();
        this.status = event.getStatus();
    }

    @CommandHandler
    public void handle(CreditAccountCommand command) {
        if(command.getAmount()<0) throw new NegativeInitialBalanceException("Negative Amount");
        AggregateLifecycle.apply(new AccountCreditedEvent(
                command.getId(),
                command.getCurrency(),
                command.getAmount()
        ));
    }

    @EventSourcingHandler
    public void on(AccountCreditedEvent event) {
        this.balance += event.getAmount();
    }

    @CommandHandler
    public void handle(DebitAccountCommand command) {
        if(command.getAmount()<0) throw new NegativeInitialBalanceException("Negative Amount");
        if(command.getAmount()>this.balance) throw new RuntimeException("Balance insufficient Exception");
        AggregateLifecycle.apply(new AccountDebitedEvent(
                command.getId(),
                command.getCurrency(),
                command.getAmount()
        ));
    }

    @EventSourcingHandler
    public void on(AccountDebitedEvent event) {
        this.balance -= event.getAmount();
    }
}
