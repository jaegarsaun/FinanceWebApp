package com.jaegarsaun.finance.Controller;

import com.jaegarsaun.finance.DTO.AccountResponseDTO;
import com.jaegarsaun.finance.Service.AccountService;
import com.jaegarsaun.finance.model.Account;
import com.jaegarsaun.finance.repository.AccountRepository;
import com.jaegarsaun.finance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<AccountResponseDTO> getAccountByUserId(@PathVariable Integer userId) {
        return userRepository.findById(userId)
                .map(user -> accountRepository.findByUser(user))
                .map(account -> new AccountResponseDTO(account.getAccountId(), account.getBalance(), account.getSavings(), account.getIncome(), account.getExpenses(), account.getCreatedAt(), account.getUpdatedAt()))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/update")
    public ResponseEntity<AccountResponseDTO> updateAccount(@RequestBody Account account) {
        Account updatedAccount = accountService.updateAccount(account); // This should return the updated account
        if (updatedAccount != null) {
            AccountResponseDTO responseDTO = new AccountResponseDTO(updatedAccount.getAccountId(), updatedAccount.getBalance(), updatedAccount.getSavings(), updatedAccount.getIncome(), updatedAccount.getExpenses(), updatedAccount.getCreatedAt(), updatedAccount.getUpdatedAt());
            return ResponseEntity.ok(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

