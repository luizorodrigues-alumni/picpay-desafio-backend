package com.luiz.transactionssimplified.service;

import com.luiz.transactionssimplified.infrastructure.clients.NotificationClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationClient client;

    public void sendNotification(){
        client.sendNotification();
    }
}
