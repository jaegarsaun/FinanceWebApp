package com.jaegarsaun.finance.Service;

import com.jaegarsaun.finance.model.Account;
import com.jaegarsaun.finance.model.Expenses;
import com.jaegarsaun.finance.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public Expenses createExpense(Account account, int userId, String expenseType, String accountFrom, Float accountTo, Date executionDate, float amount) {
        Expenses expense = new Expenses();
        expense.setAccount(account); // Set the account object instead of just the accountId
        expense.setUserId(userId);
        expense.setExpenseType(expenseType);
        expense.setAccountFrom(accountFrom);
        expense.setAccountTo(accountTo);
        expense.setExecutionDate(executionDate);
        expense.setAmount(amount);

        return expenseRepository.save(expense);
    }

    public List<Expenses> getExpensesForToday() {
        LocalDate today = LocalDate.now();
        return expenseRepository.findByExecutionDate(today);
    }
}
