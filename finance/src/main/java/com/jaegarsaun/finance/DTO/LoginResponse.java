package com.jaegarsaun.finance.DTO;

public class LoginResponse {
    private String message;
    private Integer userId;

    public LoginResponse(String message, Integer userId) {
        this.message = message;
        this.userId = userId;
    }

    // Getters and setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}

