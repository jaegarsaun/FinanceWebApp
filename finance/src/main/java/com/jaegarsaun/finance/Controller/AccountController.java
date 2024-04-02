package com.jaegarsaun.finance.Controller;

import com.jaegarsaun.finance.DTO.AccountHistoryDTO;
import com.jaegarsaun.finance.model.Account;
import com.jaegarsaun.finance.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/history/{accountId}")
    public ResponseEntity<AccountHistoryDTO> getAccountHistory(@PathVariable Integer accountId) {
        AccountHistoryDTO accountHistory = accountService.getAccountHistory(accountId);
        if (accountHistory.getChanges().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(accountHistory);
    }

}
