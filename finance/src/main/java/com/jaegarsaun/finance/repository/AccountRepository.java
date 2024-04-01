package com.jaegarsaun.finance.repository;

import com.jaegarsaun.finance.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByUserUserId(Integer userId);
}

