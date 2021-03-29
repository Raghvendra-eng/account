package com.user.account.services;

import com.user.account.entity.User;

public interface AccountServices {
	
	public User updateUser(User user);
	
	public User getUser(long accountNumber);
}
