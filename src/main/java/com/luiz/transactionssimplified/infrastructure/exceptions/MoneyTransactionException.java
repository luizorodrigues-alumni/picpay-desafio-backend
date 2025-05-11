package com.luiz.transactionssimplified.infrastructure.exceptions;

public class MoneyTransactionException extends RuntimeException {
    public MoneyTransactionException(String message) {
        super(message);
    }
}
