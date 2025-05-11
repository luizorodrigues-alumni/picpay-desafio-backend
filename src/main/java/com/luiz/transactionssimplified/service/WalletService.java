package com.luiz.transactionssimplified.service;

import com.luiz.transactionssimplified.infrastructure.entity.Wallet;
import com.luiz.transactionssimplified.infrastructure.repository.WalletRepository;
import org.springframework.stereotype.Service;

@Service
public class WalletService {
    private WalletRepository repository;

    public WalletService(WalletRepository repository) {
        this.repository = repository;
    }

    public Wallet saveWallet(Wallet wallet){
       return repository.save(wallet);
    }
}
