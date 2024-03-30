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

    public boolean authenticate(String username, String password) {
        // Attempt to find a user by the username
        User user = userRepository.findByUsername(username);

        // Check if a user was found and if the password matches
        return user != null && user.getPassword().equals(password); // The user is authenticated

        // No user found or password does not match
    }

    @Transactional
    public User register(User user) {
        // Save the new user
        User newUser = userRepository.save(user);

        // Create a new account for the user
        Account account = new Account();
        account.setUser(newUser); // Set the user
        account.setBalance(0.0F); // Initial values
        account.setSavings(0.0F);
        account.setIncome(0.0F);
        account.setExpenses(0.0F);

        accountRepository.save(account);

        newUser.setAccount(account); // Link the account back to the user if needed

        return newUser;
    }

    public User findByUsername(String username) {

        return userRepository.findByUsername(username);
    }
}



