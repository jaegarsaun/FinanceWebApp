package com.jaegarsaun.finance.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.envers.Audited;
@Data
@Entity
@Audited
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountId;

    @Column(nullable = false)
    private Float balance;

    @Column(nullable = false)
    private Float income;

    @Column(nullable = false)
    private Float savings;

    @Column(nullable = false)
    private Float expenses;

    @OneToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;


}



