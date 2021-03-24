package com.user.account.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.account.entity.User;
import com.user.account.repo.RepoLayer;

@Service
public class AccountServiceClass implements AccountServices {

	@Autowired
	private RepoLayer repoLayerVari;
	
	@Override
	public User updateUser(User user) {
		return  repoLayerVari.save(user);
	}

	@Override
	public User getUser(long accountNumber) {
		// TODO Auto-generated method stub
		return repoLayerVari.getOne(accountNumber);
	}

}
