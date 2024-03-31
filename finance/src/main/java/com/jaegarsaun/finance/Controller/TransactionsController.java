package com.jaegarsaun.finance.Controller;

import com.jaegarsaun.finance.DTO.TransactionDTO;
import com.jaegarsaun.finance.Service.TransactionService;
import com.jaegarsaun.finance.model.Transaction;
import com.jaegarsaun.finance.model.User;
import com.jaegarsaun.finance.repository.TransactionRepository;
import com.jaegarsaun.finance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transactions")
public class TransactionsController {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TransactionDTO>> getTransactionsByUserId(@PathVariable Integer userId) {
        List<TransactionDTO> transactions = transactionService.findTransactionsByUserId(userId);
        return ResponseEntity.ok(transactions);
    }

    @PostMapping("/create")
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO transactionDTO) {
        User user = userRepository.findById(transactionDTO.getUserID()).orElseThrow(() -> new RuntimeException("User not found"));
        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setTransactionTypeId(transactionDTO.getTransactionTypeId());
        transaction.setCost(transactionDTO.getCost());
        transaction.setPostedAt(transactionDTO.getPostedAt());

        Transaction savedTransaction = transactionRepository.save(transaction);
        return ResponseEntity.ok(savedTransaction);
    }
}
