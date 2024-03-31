package com.jaegarsaun.finance.repository;

import com.jaegarsaun.finance.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findByUserUserId(Integer userId);
}
