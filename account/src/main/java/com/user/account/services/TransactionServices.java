package com.user.account.services;

import java.util.ArrayList;

import com.user.account.entity.Transactions;

public interface TransactionServices {
	
	public Transactions addTransaction(Transactions newTransaction);
	
	public ArrayList< Transactions> getSummary(long accountNumber);
}
