package com.user.account.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name ="transactions" )
@NoArgsConstructor
@AllArgsConstructor
@Getter 
@Setter
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
}
