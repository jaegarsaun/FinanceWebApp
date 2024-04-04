package com.jaegarsaun.finance.Service;

import com.jaegarsaun.finance.model.Account;
import com.jaegarsaun.finance.model.Expenses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DailyExpenseCheckTask {

    @Autowired
    private ExpenseService expenseService;

    @Scheduled(cron = "0 0 0 * * *") // Run at midnight every day
    public void checkExpensesForToday() {
        List<Expenses> expensesForToday = expenseService.getExpensesForToday();

        // Perform actions for expenses with today's date
        for (Expenses expense : expensesForToday) {
            //get the account
            Account account = expense.getAccount();
            switch(expense.getExpenseType()){
                case "Withdrawal":
                    // withdraw the amount from the account
                    switch(expense.getAccountFrom()){
                        case "balance":
                            account.setBalance((account.getBalance() - expense.getAmount()));
                            break;
                        case "savings":
                            account.setSavings((account.getSavings() - expense.getAmount()));
                            break;
                        default:
                            break;
                    }
                    break;
                case "Transfer":
                    // Transfer from account A to account B
                    switch(expense.getAccountFrom()){
                        case "balance":
                            if(expense.getAccountTo() == "savings"){
                                // subtract amount from balance
                                account.setBalance(account.getBalance() - expense.getAmount());
                                // add amount to savings
                                account.setSavings(account.getSavings() + expense.getAmount());
                            }
                            break;
                        case "savings":
                            if(expense.getAccountTo() == "balance"){
                                // add amount to balance
                                account.setBalance(account.getBalance() + expense.getAmount());
                                // subtract amount from savings
                                account.setSavings(account.getSavings() - expense.getAmount());
                            }
                            break;
                        default:
                            break;
                    }
                    break;
                case "Deposit":
                    switch(expense.getAccountFrom()){
                        case "savings":
                            // add amount to savings
                            account.setSavings(account.getSavings() + expense.getAmount());
                            break;
                        case "balance":
                            // add amount to balance
                            account.setBalance(account.getBalance() + expense.getAmount());
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
    }
}

