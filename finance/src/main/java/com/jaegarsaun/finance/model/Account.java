package com.jaegarsaun.finance.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountId;
    private Integer userId;
    private Float balance;
    private Float savings;
    private Float income;
    private Float expenses;

    // Default Constructor
    public Account() {
    }

    // Constructor with all fields
    public Account(Integer accountId, Integer userId, Float balance, Float savings, Float income, Float expenses) {
        this.accountId = accountId;
        this.userId = userId;
        this.balance = balance;
        this.savings = savings;
        this.income = income;
        this.expenses = expenses;
    }

    // Getters
    public Integer getAccountId() {
        return accountId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Float getBalance() {
        return balance;
    }

    public Float getSavings() {
        return savings;
    }

    public Float getIncome() {
        return income;
    }

    public Float getExpenses() {
        return expenses;
    }

    // Setters
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public void setSavings(Float savings) {
        this.savings = savings;
    }

    public void setIncome(Float income) {
        this.income = income;
    }

    public void setExpenses(Float expenses) {
        this.expenses = expenses;
    }
}

