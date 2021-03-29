package com.user.account.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.account.entity.Transactions;
import com.user.account.repositories.TransactionRepository;

@Service
public class UserTransactionService implements TransactionService {
	
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Override
	public Transactions addTransaction(Transactions newTransaction) {
		return transactionRepository.save(newTransaction);
	}

	@Override
	public ArrayList<Transactions> getSummary(long accountNumber) {
		return transactionRepository.findByAccountNumber(accountNumber);
	}

}
