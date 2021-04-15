package com.user.account.services.serviceImplementation;

import com.user.account.advice.CustomExceptions;
import com.user.account.message.DefaultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.user.account.entity.User;
import com.user.account.repositories.UserRepository;

@Service
public class AccountServiceClass implements AccountService {

    @Autowired
    private UserRepository userRepository;
    
    @Override
    public User updateUser(User user) {
        return  userRepository.save(user);
    }

    @Override
    public User getUser(Long accountNumber) throws CustomExceptions {
        if(!userRepository.existsById(accountNumber))
            throw new CustomExceptions(DefaultMessage.INVALID_ACCOUNT_NUMBER);
        return userRepository.getOne(accountNumber);
    }
}