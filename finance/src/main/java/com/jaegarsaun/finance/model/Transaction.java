package com.jaegarsaun.finance.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;


    @Column(name = "transactionTypeId")
    private Integer transactionTypeId;

    private Float cost;


    @ManyToOne
    @JoinColumn(name = "userID", referencedColumnName = "userId")
    private User user;  // Map to the User entity


    @Column(name = "postedAt")
    private LocalDateTime postedAt;


}


