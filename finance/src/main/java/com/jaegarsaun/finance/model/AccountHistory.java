package com.jaegarsaun.finance.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "account_history")
public class AccountHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyId;

    @Column(name = "account_id")
    private Integer accountId;

    private BigDecimal income;
    private BigDecimal balance;
    private BigDecimal expenses;
    private BigDecimal savings;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    private String action;

}

