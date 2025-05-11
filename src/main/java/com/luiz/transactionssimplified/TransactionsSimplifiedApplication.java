package com.luiz.transactionssimplified;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TransactionsSimplifiedApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionsSimplifiedApplication.class, args);
    }

}
