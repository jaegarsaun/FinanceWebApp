package com.jaegarsaun.finance.repository;

import com.jaegarsaun.finance.model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionTypeRepository extends JpaRepository<TransactionType, Integer> {
}

