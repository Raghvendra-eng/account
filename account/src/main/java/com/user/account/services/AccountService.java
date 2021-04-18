package com.user.account.services;

import com.user.account.advice.UserNotFoundException;
import com.user.account.entity.User;

public interface AccountService {
    User updateUser(User user);
    User getUser(Long accountNumber) throws UserNotFoundException;
}
