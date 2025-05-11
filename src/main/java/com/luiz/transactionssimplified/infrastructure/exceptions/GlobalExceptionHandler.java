package com.luiz.transactionssimplified.infrastructure.exceptions;

import com.luiz.transactionssimplified.infrastructure.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Money Transaction Exception
    @ExceptionHandler(MoneyTransactionException.class)
    public ResponseEntity<ErrorDTO> handleMoneyTransactionException(MoneyTransactionException e){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDTO(e.getMessage()));
    }

    // User Not Found Exception
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleUserNotFoundException(UserNotFoundException e){
        return ResponseEntity
                .status(404)
                .body(new ErrorDTO(e.getMessage()));
    }
}
