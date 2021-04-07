package com.user.account.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@Entity
@Data
public class User {
	
	@Id
	private long accountNumber;
	
	private long accountBalance;
	
	private String name;
	
	public void incrementAccountBalance( long amount) {
		accountBalance = accountBalance + amount; 
	}

	
}
