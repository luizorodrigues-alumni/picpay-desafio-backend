package com.luiz.transactionssimplified.infrastructure.dto;

import java.math.BigDecimal;

public record TransactionDto(BigDecimal amount, Long payer_id, Long payee_id) {
}
