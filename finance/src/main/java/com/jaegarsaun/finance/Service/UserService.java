package com.jaegarsaun.finance.Service;
import com.jaegarsaun.finance.model.Account;
import com.jaegarsaun.finance.model.User;
import com.jaegarsaun.finance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountService accountService;


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

        // Create and associate an account with the new user
        Account newAccount = new Account();
        newAccount.setBalance(0f);
        newAccount.setIncome(0f);
        newAccount.setSavings(0f);
        newAccount.setExpenses(0f);
        newAccount.setUser(newUser); // Associate the account with the user

        accountService.saveOrUpdateAccount(newAccount); // Save the new account

        newUser.setAccount(newAccount); // Set the created account to the new user for bidirectional association

        return newUser; // Return the new user with the associated account
    }

    public User findByUsername(String username) {

        return userRepository.findByUsername(username);
    }
}



