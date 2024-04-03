package com.jaegarsaun.finance.Service;

import com.jaegarsaun.finance.model.Account;
import com.jaegarsaun.finance.repository.AccountRepository;
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





}

