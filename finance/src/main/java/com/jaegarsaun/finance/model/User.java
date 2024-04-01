package com.jaegarsaun.finance.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.envers.Audited;

@Data
@Entity
@Audited
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String fName;
    private String lName;
    private String password;
    private String username;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @ToString.Exclude // Exclude this field from the toString() method
    @JsonBackReference
    private Account account;
}


