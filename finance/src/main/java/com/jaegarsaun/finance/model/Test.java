package com.jaegarsaun.finance.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Test {
    @Id
    private Long id; // Assuming there's an ID column

    private String value;

    // Constructors, getters, and setters

    public Test() {
    }

    public Test(String value) {
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}

