package com.luiz.transactionssimplified.infrastructure.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "notificationClient", url = "${notification.client.url}")
public interface NotificationClient {
    @PostMapping
    void sendNotification();
}
