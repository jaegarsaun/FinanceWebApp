package com.jaegarsaun.finance.Service;

import com.jaegarsaun.finance.model.Account;
import com.jaegarsaun.finance.model.AccountHistory;
import com.jaegarsaun.finance.repository.AccountRepository;
import com.jaegarsaun.finance.repository.AccountHistoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountHistoryRepository accountHistoryRepository;

    @Transactional
    public Account updateAccount(Account account) {
        // Before saving, fetch the current state and compare if needed
        Account currentAccount = accountRepository.findById(account.getAccountId()).orElse(null);

        if (currentAccount != null) {
            // Potentially perform comparison or additional logic here

            // Save the updated account
            Account updatedAccount = accountRepository.save(account);

            // Log the change to the history table
            AccountHistory history = new AccountHistory();
            history.setAccountId(updatedAccount.getAccountId());
            history.setIncome(BigDecimal.valueOf(updatedAccount.getIncome()));
            history.setBalance(BigDecimal.valueOf(updatedAccount.getBalance()));
            history.setExpenses(BigDecimal.valueOf(updatedAccount.getExpenses()));
            history.setSavings(BigDecimal.valueOf(updatedAccount.getSavings()));
            history.setAction("update");
            accountHistoryRepository.save(history);

            // Optionally, call a method to clean up old history records
            cleanupHistory(updatedAccount.getAccountId());

            return updatedAccount;
        }

        return null; // Or handle differently if the account does not exist.
    }


    private void cleanupHistory(Integer accountId) {
        // Get all history records for the account, ordered by updatedAt descending (newest first)
        List<AccountHistory> histories = accountHistoryRepository.findByAccountIdOrderByUpdatedAtDesc(accountId);

        // If there are more than 10 history records, delete the oldest ones
        if (histories.size() > 10) {
            // This gets the sub-list from the 11th element to the end of the list
            List<AccountHistory> toDelete = histories.subList(10, histories.size());
            // Delete the old history records
            accountHistoryRepository.deleteAll(toDelete);
        }
    }
}

