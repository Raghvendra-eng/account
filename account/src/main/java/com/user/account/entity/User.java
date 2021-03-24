package com.user.account.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id
	private long accountNumber;
	private double accountBalance;
	private String name;
	
	public User(long accountNumber, double accountBalance, String name){
		this.accountNumber = accountNumber;
		this.accountBalance = accountBalance;
		this.name = name;
	}
	
	public void setAccountNumber ( long number) {
		this.accountNumber = number;
	}
	
	public double getAccountNumber ( ) {
		return this.accountNumber;
	}
	
	public double getAccountBalance() {
		return this.accountBalance;
	}
	
	public void setAccountBalance( double balance) {
		this.accountBalance = balance;
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void updateBalanceBy( double amount) {
		this.accountBalance = this.accountBalance + amount; 
	}
	
}
