package com.jaegarsaun.finance.Controller;

import com.jaegarsaun.finance.model.Account;
import com.jaegarsaun.finance.Service.AccountService;
import com.jaegarsaun.finance.repository.AccountAuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;


    @Autowired
    private AccountAuditRepository accountAuditRepository;

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
    public ResponseEntity<Account> updateAccount(@PathVariable Integer id, @RequestBody Map<String, Float> updatedFields) {
        Optional<Account> optionalAccount = accountService.findById(id);
        if (optionalAccount.isPresent()) {
            Account existingAccount = optionalAccount.get();

            // Update the fields of existingAccount with the values from updatedFields
            if (updatedFields.containsKey("balance")) {
                existingAccount.setBalance(updatedFields.get("balance"));
            }
            if (updatedFields.containsKey("income")) {
                existingAccount.setIncome(updatedFields.get("income"));
            }
            if (updatedFields.containsKey("savings")) {
                existingAccount.setSavings(updatedFields.get("savings"));
            }
            if (updatedFields.containsKey("expenses")) {
                existingAccount.setExpenses(updatedFields.get("expenses"));
            }

            // Save the updated account
            Account savedAccount = accountService.saveOrUpdateAccount(existingAccount);

            return ResponseEntity.ok(savedAccount);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/history/user/{userId}")
    public ResponseEntity<List<Map<String, Object>>> getAccountAuditsByUserId(@PathVariable int userId) {
        List<Map<String, Object>> accountAudits = accountAuditRepository.findByUserId(userId);
        if (accountAudits.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(accountAudits);
    }


}
