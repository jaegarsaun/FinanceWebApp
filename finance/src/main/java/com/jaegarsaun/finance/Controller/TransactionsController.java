package com.jaegarsaun.finance.Controller;

import com.jaegarsaun.finance.DTO.TransactionDTO;
import com.jaegarsaun.finance.Service.TransactionService;
import com.jaegarsaun.finance.model.Transaction;
import com.jaegarsaun.finance.model.User;
import com.jaegarsaun.finance.repository.AccountRepository;
import com.jaegarsaun.finance.repository.TransactionRepository;
import com.jaegarsaun.finance.repository.UserRepository;
import com.jaegarsaun.finance.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transactions")
public class TransactionsController {
    LocalDateTime today = LocalDateTime.now();
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;

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
        Account account = user.getAccount(); // Assuming a getUserAccount method or similar exists

        switch(transactionDTO.getTransactionTypeId()) {
            case 1: // Assuming 1 is Deposit
                updateAccountOnDepositOrWithdrawal(account, transactionDTO, true);
                break;
            case 2: // Assuming 2 is Withdrawal/Expense
                updateAccountOnDepositOrWithdrawal(account, transactionDTO, false);
                break;
            case 3: // Assuming 3 is Transfer
                updateAccountOnTransfer(account, transactionDTO);
                break;
            case 4:
                updateAccountOnDepositOrWithdrawal(account, transactionDTO, false);
                break;
            default:
                break;
            // Handle other cases as necessary
        }

        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setTransactionTypeId(transactionDTO.getTransactionTypeId());
        transaction.setCost(transactionDTO.getCost());
        transaction.setPostedAt(LocalDateTime.now()); // Consider using the time from DTO if applicable
        Transaction savedTransaction = transactionRepository.save(transaction);
        return ResponseEntity.ok(savedTransaction);
    }

    private void updateAccountOnDepositOrWithdrawal(Account account, TransactionDTO transactionDTO, boolean isDeposit) {
        float amount = isDeposit ? transactionDTO.getCost() : -transactionDTO.getCost();
        if ("savings".equalsIgnoreCase(transactionDTO.getAccountFrom())) {
            account.setSavings(account.getSavings() + amount);
        } else if ("balance".equalsIgnoreCase(transactionDTO.getAccountFrom())) {
            account.setBalance(account.getBalance() + amount);
        }
        // Persist changes to the account
        // accountRepository.save(account); // Assuming you have an account repository
    }

    private void updateAccountOnTransfer(Account account, TransactionDTO transactionDTO) {
        if ("savings".equalsIgnoreCase(transactionDTO.getAccountFrom()) && "balance".equalsIgnoreCase(transactionDTO.getAccountTo())) {
            account.setSavings(account.getSavings() - transactionDTO.getCost());
            account.setBalance(account.getBalance() + transactionDTO.getCost());
        } else if ("balance".equalsIgnoreCase(transactionDTO.getAccountFrom()) && "savings".equalsIgnoreCase(transactionDTO.getAccountTo())) {
            account.setBalance(account.getBalance() - transactionDTO.getCost());
            account.setSavings(account.getSavings() + transactionDTO.getCost());
        }
        // Persist changes to the account
         accountRepository.save(account); // Assuming you have an account repository
    }
}
