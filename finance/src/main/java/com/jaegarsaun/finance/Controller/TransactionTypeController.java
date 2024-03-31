package com.jaegarsaun.finance.controller;

import com.jaegarsaun.finance.model.TransactionType;
import com.jaegarsaun.finance.repository.TransactionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/transaction-types")
public class TransactionTypeController {

    @Autowired
    private TransactionTypeRepository transactionTypeRepository;

    @GetMapping
    public ResponseEntity<List<TransactionType>> getAllTransactionTypes() {
        List<TransactionType> transactionTypes = transactionTypeRepository.findAll();
        return ResponseEntity.ok(transactionTypes);
    }
}

