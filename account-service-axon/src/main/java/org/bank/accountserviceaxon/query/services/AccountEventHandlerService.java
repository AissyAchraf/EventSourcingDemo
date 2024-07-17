package org.bank.accountserviceaxon.query.services;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.queryhandling.QueryHandler;
import org.bank.accountserviceaxon.commonapi.events.AccountCreatedEvent;
import org.bank.accountserviceaxon.query.entities.Account;
import org.bank.accountserviceaxon.query.queries.GetAllAccounts;
import org.bank.accountserviceaxon.query.repository.AccountRepository;
import org.bank.accountserviceaxon.query.repository.TransactionAccountRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class AccountEventHandlerService {

    private AccountRepository accountRepository;
    private TransactionAccountRepository transactionAccountRepository;


    public AccountEventHandlerService(AccountRepository accountRepository, TransactionAccountRepository transactionAccountRepository) {
        this.accountRepository = accountRepository;
        this.transactionAccountRepository = transactionAccountRepository;
    }

    @EventHandler
    public void on(AccountCreatedEvent event, EventMessage<AccountCreatedEvent> eventMessage) {
        log.info("*************************************************");
        log.info("AccountRepository received");
        Account account = new Account();
        account.setId(event.getId());
        account.setBalance(event.getBalance());
        account.setStatus(event.getStatus());
        account.setCurrency(event.getCurrency());
        account.setCreatedAt(eventMessage.getTimestamp());
        accountRepository.save(account);
    }

    @QueryHandler
    public List<Account> on(GetAllAccounts query) {
        return  accountRepository.findAll();
    }
}
