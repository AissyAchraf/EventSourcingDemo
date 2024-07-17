package org.bank.accountserviceaxon.query.controllers;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.bank.accountserviceaxon.query.entities.Account;
import org.bank.accountserviceaxon.query.queries.GetAllAccounts;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController("/query/")
public class AccountQueryController {

    private QueryGateway queryGateway;

    public AccountQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("/list")
    public CompletableFuture<List<Account>> accountList() {
        return queryGateway.query(new GetAllAccounts(), ResponseTypes.multipleInstancesOf(Account.class));
    }
}
