package com.user.account.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.user.account.entity.User;
import com.user.account.entity.Transactions;
import com.user.account.services.AccountServices;
import com.user.account.services.TransactionServices;



@RestController
public class AllControlers {
	
	
		@Autowired
		private AccountServices accountServices;
		
		@Autowired
		private TransactionServices transactionServices;
		
		// tomcat test
		@GetMapping("/home")
		public String home() {
			return "This is the home page";
		}
		
		// Updating accountBalance after debit
		
		
		@PostMapping("/debit/{accountNumber}")
		public User debit(@PathVariable String accountNumber, @RequestBody String amount) {
			
			User user = this.accountServices.getUser(Long.parseLong(accountNumber));
			
			if ( Long.parseLong(amount) <= user.getAccountBalance() ) {
				
				user.incrementAccountBalance(-Long.parseLong(amount)) ;
				
				Transactions newTransaction = new Transactions(user.getAccountNumber(), Long.parseLong(amount), "debit");
				
				this.transactionServices.addTransaction(newTransaction);
				
				return this.accountServices.updateUser(user);
			}
			
			else 
				return user;
		}
		
		// Updating accountBalance after credit
		
		@PostMapping("/credit/{accountNumber}")
		public User credit(@PathVariable String accountNumber, @RequestBody String amount) {
				
			User user = this.accountServices.getUser(Long.parseLong(accountNumber));
				
			user.incrementAccountBalance(Long.parseLong(amount)) ;
				
			Transactions newTransaction = new Transactions(user.getAccountNumber(), Long.parseLong(amount), "credit");
				
			this.transactionServices.addTransaction(newTransaction);
				
			return this.accountServices.updateUser(user);
				
		}
		
		// GetSummary for transactions in an account
		
		@GetMapping("/getSummary/{accountNumber}")
		
		public ArrayList < Transactions > transactionSummary(@PathVariable String accountNumber){
			
			return this.transactionServices.getSummary(Long.parseLong(accountNumber));
		}
}
