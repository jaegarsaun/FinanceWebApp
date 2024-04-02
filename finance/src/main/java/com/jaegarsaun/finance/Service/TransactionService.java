package com.jaegarsaun.finance.Service;

import com.jaegarsaun.finance.DTO.TransactionDTO;
import com.jaegarsaun.finance.model.Transaction;
import com.jaegarsaun.finance.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<TransactionDTO> findTransactionsByUserId(Integer userId) {
        List<Transaction> transactions = transactionRepository.findByUserUserId(userId);
        return transactions.stream().map(this::convertToTransactionDTO).collect(Collectors.toList());
    }

    private TransactionDTO convertToTransactionDTO(Transaction transaction) {
        TransactionDTO dto = new TransactionDTO();
        dto.setTransactionTypeId(transaction.getTransactionTypeId());
        dto.setCost(transaction.getCost());
        dto.setUserID(transaction.getUser().getUserId());
        dto.setPostedAt(transaction.getPostedAt());
        return dto;
    }
}
