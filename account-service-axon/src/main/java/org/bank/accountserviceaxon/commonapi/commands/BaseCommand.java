package org.bank.accountserviceaxon.commonapi.commands;

import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class BaseCommand<T> {

    @TargetAggregateIdentifier
    @Getter private T id;


    protected BaseCommand(T id) {
        this.id = id;
    }
}
