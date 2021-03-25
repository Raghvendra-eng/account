package com.user.account.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.account.entity.Transactions;
import com.user.account.repo.TransactionRepoLayerInFace;

@Service
public class UserTransactionServices implements TransactionServices {
	
	
	@Autowired
	private TransactionRepoLayerInFace transactionVari;
	
	@Override
	public Transactions addTransaction(Transactions newTransaction) {
		// TODO Auto-generated method stub
		return transactionVari.save(newTransaction);
	}

	@Override
	public ArrayList<Transactions> getSummary(long accountNumber) {
		// TODO Auto-generated method stub
		return transactionVari.findByAccountNumber(accountNumber);
	}

}