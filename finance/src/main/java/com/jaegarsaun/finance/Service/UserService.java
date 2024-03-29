package com.jaegarsaun.finance.Service;
import com.jaegarsaun.finance.model.Account;
import com.jaegarsaun.finance.model.User;
import com.jaegarsaun.finance.repository.AccountRepository;
import com.jaegarsaun.finance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public User register(User user) {
        // Save the new user
        User newUser = userRepository.save(user);

        // Create a new account for the user
        Account account = new Account();
        account.setUserId(newUser.getUserId());
        account.setBalance(0.0F); // Set initial balance to 0
        account.setSavings(0.0F); // Set initial savings to 0
        account.setIncome(0.0F);
        account.setExpenses(0.0F);

        accountRepository.save(account);

        return newUser;
    }
}



