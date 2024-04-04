package com.jaegarsaun.finance.model;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
@Data
@Entity
public class Expenses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "expense_type", nullable = false)
    private String expenseType;

    @Column(name = "account_from", nullable = false)
    private String accountFrom;

    @Column(name = "account_to")
    private String accountTo;

    @Column(name = "execution_date", nullable = false)
    private Date executionDate;

    @Column(nullable = false)
    private float amount;

}

