package org.bank.accountserviceaxon.query.repository;

import org.bank.accountserviceaxon.query.entities.AccountTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionAccountRepository extends JpaRepository<AccountTransaction, Long> {
}
