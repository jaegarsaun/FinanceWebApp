package com.jaegarsaun.finance.DTO;

import java.time.LocalDateTime;

public class AccountResponseDTO {
    private Integer accountId;
    private Float balance;
    private Float savings;
    private Float income;
    private Float expenses;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Default constructor
    public AccountResponseDTO() {
    }

    // Constructor with all fields including createdAt and updatedAt
    public AccountResponseDTO(Integer accountId, Float balance, Float savings, Float income, Float expenses, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.accountId = accountId;
        this.balance = balance;
        this.savings = savings;
        this.income = income;
        this.expenses = expenses;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters for all fields
    // Include getters and setters for createdAt and updatedAt

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    // Getters
    public Integer getAccountId() {
        return accountId;
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

