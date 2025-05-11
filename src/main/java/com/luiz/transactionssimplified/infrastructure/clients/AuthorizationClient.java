package com.luiz.transactionssimplified.infrastructure.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "authorizationClient", url = "${authorization.client.url}")
public interface AuthorizationClient {
    @GetMapping
    AuthorizationDto authorize();
}
