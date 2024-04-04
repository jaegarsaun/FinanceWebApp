package com.jaegarsaun.finance.Service;

import com.jaegarsaun.finance.model.Account;
import com.jaegarsaun.finance.model.Expenses;
import com.jaegarsaun.finance.repository.AccountRepository;
import com.jaegarsaun.finance.repository.ExpenseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ExpenseRepository expenseRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<Account> findById(Integer accountId) {
        return accountRepository.findById(accountId);
    }

    public Account saveOrUpdateAccount(Account account) {
        return accountRepository.save(account);
    }
    public Optional<Account> findByUserId(Integer userId) {
        return Optional.ofNullable(accountRepository.findByUserUserId(userId));
    }

    public void updateAccountExpenses(Account account) {
        int userId = account.getUser().getUserId();
        List<Expenses> userExpenses = expenseRepository.findByUserId(userId);

        float totalExpenses = 0;
        for (Expenses expense : userExpenses) {
            totalExpenses += expense.getAmount();
        }

        account.setExpenses(totalExpenses);
        accountRepository.save(account);
    }





}

