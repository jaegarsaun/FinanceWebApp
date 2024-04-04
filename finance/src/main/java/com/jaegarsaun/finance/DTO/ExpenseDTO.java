package com.jaegarsaun.finance.DTO;

import java.util.Date;

public class ExpenseDTO {
    private int accountId;
    private int userId;
    private String expenseType;
    private String accountFrom;
    private String accountTo;
    private Date executionDate;
    private float amount;

    // Constructors
    public ExpenseDTO() {}

    public ExpenseDTO(int accountId, int userId, String expenseType, String accountFrom, String accountTo, Date executionDate, float amount) {
        this.accountId = accountId;
        this.userId = userId;
        this.expenseType = expenseType;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.executionDate = executionDate;
        this.amount = amount;
    }

    // Getters and Setters
    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public String getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(String accountFrom) {
        this.accountFrom = accountFrom;
    }

    public String getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(String accountTo) {
        this.accountTo = accountTo;
    }

    public Date getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(Date executionDate) {
        this.executionDate = executionDate;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}

