package com.user.account.services;

import com.user.account.advice.CustomExceptions;
import com.user.account.entity.User;

public interface AccountService {
    User updateUser(User user);
    User getUser(Long accountNumber) throws CustomExceptions;
}
