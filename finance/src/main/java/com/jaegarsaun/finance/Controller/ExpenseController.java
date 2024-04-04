package com.jaegarsaun.finance.Controller;

import com.jaegarsaun.finance.DTO.ExpenseDTO;
import com.jaegarsaun.finance.Service.ExpenseService;
import com.jaegarsaun.finance.model.Account;
import com.jaegarsaun.finance.model.Expenses;
import com.jaegarsaun.finance.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.sql.SQLException;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;
    @Autowired
    private AccountRepository accountRepository;

    @PostMapping("/create")
    public Expenses createExpense(@Valid @RequestBody ExpenseDTO expenseDTO) throws SQLException {
        // Retrieve the account object using accountId from DTO
        Account account = accountRepository.findById(expenseDTO.getAccountId()).orElseThrow(() -> new SQLException("Account not found with id: " + expenseDTO.getAccountId()));

        // Call createExpense method with the account object
        return expenseService.createExpense(account, expenseDTO.getUserId(), expenseDTO.getExpenseType(), expenseDTO.getAccountFrom(), expenseDTO.getAccountTo(), expenseDTO.getExecutionDate(), expenseDTO.getAmount());
    }
}

