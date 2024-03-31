package com.jaegarsaun.finance.repository;

import com.jaegarsaun.finance.model.AccountHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountHistoryRepository extends JpaRepository<AccountHistory, Long> {
    List<AccountHistory> findByAccountIdOrderByUpdatedAtDesc(Integer accountId);
}
