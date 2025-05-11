package com.luiz.transactionssimplified.service;

import com.luiz.transactionssimplified.infrastructure.dto.TransactionDto;
import com.luiz.transactionssimplified.infrastructure.entity.Transactions;
import com.luiz.transactionssimplified.infrastructure.entity.User;
import com.luiz.transactionssimplified.infrastructure.entity.UserType;
import com.luiz.transactionssimplified.infrastructure.exceptions.MoneyTransactionException;
import com.luiz.transactionssimplified.infrastructure.exceptions.UserNotFoundException;
import com.luiz.transactionssimplified.infrastructure.repository.TransactionsRepository;
import com.luiz.transactionssimplified.infrastructure.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionsRepository transactionsRepository;
    private final UserRepository userRepository;
    private final AuthorizationService authorizationService;
    private final WalletService walletService;
    private final NotificationService notificationService;


    private boolean hasInsufficientBalance(User payer, BigDecimal transactionAmount){
        return payer.getWallet().getBalance().compareTo(transactionAmount) < 0;
    }

    private void updateWalletsBalance(User payer, User payee, BigDecimal amount){
        BigDecimal currentPayerBalance = payer.getWallet().getBalance();
        BigDecimal currentPayeeBalance = payee.getWallet().getBalance();

        // Updates the balance
        payer.getWallet().setBalance(currentPayerBalance.subtract(amount));
        payee.getWallet().setBalance(currentPayeeBalance.add(amount));

        // Saves the Wallet
        walletService.saveWallet(payer.getWallet());
        walletService.saveWallet(payee.getWallet());
    }

    @Transactional
    public TransactionDto createTransaction(TransactionDto dto){
        User payer = userRepository.findById(dto.payer_id())
                .orElseThrow(() -> new UserNotFoundException("Payer not found."));
        User payee = userRepository.findById(dto.payee_id())
                .orElseThrow(() -> new UserNotFoundException("Payee not found."));

        // Business Rule -> Merchant cannot make transactions
        if (payer.getUserType() == UserType.MERCHANT){
            throw new MoneyTransactionException("Merchant cannot make transactions.");
        }

        // Business Rule -> Cannot make any transaction with insufficient balance
        if(hasInsufficientBalance(payer, dto.amount())){
            throw new MoneyTransactionException("Insufficient Balance for Transaction.");
        }

        // Authorization API verification
        if (!authorizationService.validateTransaction()){
            throw new MoneyTransactionException("Fail during authorization process.");
        }

        // Updates the balances and saves the wallets
        updateWalletsBalance(payer, payee, dto.amount());

        // Saves the transaction
        Transactions transaction = new Transactions();
        transaction.setAmount(dto.amount());
        transaction.setPayee(payee);
        transaction.setPayer(payer);

        transactionsRepository.save(transaction);

        try {
            notificationService.sendNotification();
        } catch (Exception e) {
            throw new RuntimeException("Error sending notification.");
        }

        return new TransactionDto(
                transaction.getAmount(),
                transaction.getPayer().getId(),
                transaction.getPayee().getId()
        );
    }
}
