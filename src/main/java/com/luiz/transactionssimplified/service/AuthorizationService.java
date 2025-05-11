package com.luiz.transactionssimplified.service;

import com.luiz.transactionssimplified.infrastructure.clients.AuthorizationClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class AuthorizationService {

    private AuthorizationClient client;

    public boolean validateTransaction(){
        return Objects.equals(client.authorize().data().authorization(), true);
    }
}
