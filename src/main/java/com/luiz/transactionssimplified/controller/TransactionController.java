package com.luiz.transactionssimplified.controller;

import com.luiz.transactionssimplified.infrastructure.dto.TransactionDto;
import com.luiz.transactionssimplified.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfer")
@AllArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionDto> createTransaction(@RequestBody TransactionDto dto){
        TransactionDto response = transactionService.createTransaction(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
