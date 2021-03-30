package com.user.account.services;

//import java.util.ArrayList;

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
	public User getUser(long accountNumber) {
		return userRepository.getOne(accountNumber);
	}


}
