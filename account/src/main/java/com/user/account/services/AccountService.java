package com.user.account.services;

import com.user.account.entity.User;

public interface AccountService {
	
	public User updateUser(User user);
	
	public User getUser(Long accountNumber);
}
