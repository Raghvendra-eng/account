package com.user.account.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter 
@Setter
public class User {
	
	@Id
	private long accountNumber;
	
	private long accountBalance;
	
	private String name;
	
	public void incrementAccountBalance( long amount) {
		accountBalance = accountBalance + amount; 
	}

	
}
