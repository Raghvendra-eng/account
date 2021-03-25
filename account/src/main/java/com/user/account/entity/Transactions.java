package com.user.account.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name ="transactions" )
public class Transactions {
	
	@Id
	private long transactionID;
	private long accountNumber;
	private long amount;
	private String transactionType;
	private static long maxValue = 1;
	
	//Constructor
	public Transactions ( long accountNumber, long amount, String transactionType){
		this.transactionID = maxValue;
		maxValue++;
		this.accountNumber = accountNumber;
		this.amount = amount;
		this.transactionType = transactionType;
	}
	
	public Transactions() {
		
	}
	
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	
}
