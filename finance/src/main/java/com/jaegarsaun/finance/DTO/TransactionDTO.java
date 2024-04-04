package com.jaegarsaun.finance.DTO;

import java.time.LocalDateTime;

public class TransactionDTO {
    private Integer transactionTypeId;
    private Float cost;
    private Integer userID;
    private LocalDateTime postedAt;
    private String accountFrom; // "balance" or "savings"
    private String accountTo; // Used for transfers, can be "balance" or "savings"

    // Getters and Setters
    public String getAccountFrom() {return accountFrom;}
    public void setAccountFrom(String accountFrom){this.accountFrom = accountFrom; };

    public String getAccountTo() {return accountTo; }
    public void setAccountTo(String accountTo) {this.accountTo = accountTo; };
    public Integer getTransactionTypeId() {
        return transactionTypeId;
    }

    public void setTransactionTypeId(Integer transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public LocalDateTime getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(LocalDateTime postedAt) {
        this.postedAt = postedAt;
    }
}

