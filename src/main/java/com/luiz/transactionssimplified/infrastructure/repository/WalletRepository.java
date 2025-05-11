package com.luiz.transactionssimplified.infrastructure.repository;

import com.luiz.transactionssimplified.infrastructure.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
