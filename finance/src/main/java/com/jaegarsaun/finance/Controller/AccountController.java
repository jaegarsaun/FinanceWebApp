package com.jaegarsaun.finance.Controller;

import com.jaegarsaun.finance.DTO.AccountHistoryDTO;
import com.jaegarsaun.finance.model.Account;
import com.jaegarsaun.finance.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Integer id) {
        return accountService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping("/")
    public ResponseEntity<Account> createOrUpdateAccount(@RequestBody Account account) {
        Account savedAccount = accountService.saveOrUpdateAccount(account);
        return ResponseEntity.ok(savedAccount);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Account> getAccountByUserId(@PathVariable Integer userId) {
        return accountService.findByUserId(userId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable Integer id, @RequestBody Account updatedAccount) {
        Optional<Account> optionalAccount = accountService.findById(id);
        if (optionalAccount.isPresent()) {
            Account existingAccount = optionalAccount.get();
            // Update the fields of existingAccount with the values from updatedAccount
            existingAccount.setBalance(updatedAccount.getBalance());
            existingAccount.setIncome(updatedAccount.getIncome());
            existingAccount.setSavings(updatedAccount.getSavings());
            existingAccount.setExpenses(updatedAccount.getExpenses());

            existingAccount.setUser(updatedAccount.getUser());

            // Save the updated account
            Account savedAccount = accountService.saveOrUpdateAccount(existingAccount);

            return ResponseEntity.ok(savedAccount);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/history/{accountId}")
    public ResponseEntity<AccountHistoryDTO> getAccountHistory(@PathVariable Integer accountId) {
        AccountHistoryDTO accountHistory = accountService.getAccountHistory(accountId);
        if (accountHistory.getChanges().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(accountHistory);
    }

}
