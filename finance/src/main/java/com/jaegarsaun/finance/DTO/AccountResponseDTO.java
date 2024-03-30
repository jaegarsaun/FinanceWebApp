package com.jaegarsaun.finance.DTO;

public class AccountResponseDTO {
    private Integer accountId;
    private Float balance;
    private Float savings;
    private Float income;
    private Float expenses;

    // Default constructor
    public AccountResponseDTO() {
    }

    // Constructor with all fields
    public AccountResponseDTO(Integer accountId, Float balance, Float savings, Float income, Float expenses) {
        this.accountId = accountId;
        this.balance = balance;
        this.savings = savings;
        this.income = income;
        this.expenses = expenses;
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

