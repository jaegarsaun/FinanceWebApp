package com.jaegarsaun.finance.repository;

import com.jaegarsaun.finance.model.Account;
import com.jaegarsaun.finance.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByUser(User user);
}
