package com.luiz.transactionssimplified.infrastructure.repository;

import com.luiz.transactionssimplified.infrastructure.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionsRepository extends JpaRepository<Transactions, Long> {
}
