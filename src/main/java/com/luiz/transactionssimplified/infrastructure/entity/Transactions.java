package com.luiz.transactionssimplified.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "transactions")
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal amount;

    @JoinColumn(name = "payer_id")
    @ManyToOne
    private User payer;

    @JoinColumn(name = "payee_id")
    @ManyToOne
    private User payee;

    private LocalDateTime transactionDate;

    @PrePersist
    void prePersist(){
        transactionDate = LocalDateTime.now();
    }

}
