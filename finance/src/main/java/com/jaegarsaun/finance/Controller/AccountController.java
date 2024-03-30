package com.jaegarsaun.finance.Controller;

import com.jaegarsaun.finance.DTO.AccountResponseDTO;
import com.jaegarsaun.finance.model.Account;
import com.jaegarsaun.finance.repository.AccountRepository;
import com.jaegarsaun.finance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/user/{userId}")
    public ResponseEntity<AccountResponseDTO> getAccountByUserId(@PathVariable Integer userId) {
        return userRepository.findById(userId)
                .map(user -> accountRepository.findByUser(user))
                .map(account -> new AccountResponseDTO(account.getAccountId(), account.getBalance(), account.getSavings(), account.getIncome(), account.getExpenses()))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

