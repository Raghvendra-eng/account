package com.user.account.services;

//import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.user.account.entity.Transactions;
import com.user.account.entity.User;
import com.user.account.repo.RepoLayer;
//import com.user.account.repo.TransactionRepoLayerInFace;

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
