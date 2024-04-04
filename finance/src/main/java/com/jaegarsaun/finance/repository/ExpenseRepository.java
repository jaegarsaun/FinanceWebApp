package com.jaegarsaun.finance.repository;

import com.jaegarsaun.finance.model.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expenses, Integer> {
    @Query("SELECT e FROM Expenses e WHERE e.executionDate = :date")
    List<Expenses> findByExecutionDate(@Param("date") LocalDate date);
}

