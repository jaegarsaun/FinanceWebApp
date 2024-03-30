package com.jaegarsaun.finance.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String fName;
    private String lName;
    private String password;
    private String username;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Account account;

    // Getters and setters
}


