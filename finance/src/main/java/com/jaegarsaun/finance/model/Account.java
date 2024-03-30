package com.jaegarsaun.finance.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountId;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    @ToString.Exclude // Exclude this field from the toString() method
    private User user;

    private Float balance;
    private Float savings;
    private Float income;
    private Float expenses;
}


