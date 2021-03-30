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
import com.user.account.services.AccountService;
import com.user.account.services.TransactionService;

enum TransactionType{
	DEBIT("Debit"),
	CREDIT("Credit");
	
	private final String transactionType;
	
	TransactionType(final String transactionType){
		this.transactionType = transactionType;
	}
	
	@Override
	public String toString() {
		return transactionType;
	}
}

@RestController
public class AllControlers {
	
	
		@Autowired
		private AccountService accountService;
		
		@Autowired
		private TransactionService transactionService;
		
		// tomcat test
		@GetMapping("/home")
		public String home() {
			return "This is the home page";
		}
		
		// Updating accountBalance after debit
		
		
		@PostMapping("/debit/{accountNumber}")
		public void debit(@PathVariable String accountNumber, @RequestBody String amount) {

			long userAccountNumber = Long.parseLong(accountNumber);

			long debitAmount = Long.parseLong(amount);
			
			User user = accountService.getUser(userAccountNumber);
			
			if ( debitAmount <= user.getAccountBalance() ) {
				
				user.incrementAccountBalance(-debitAmount) ;
				
				Transactions newTransaction = 
						new Transactions(userAccountNumber, Long.parseLong(amount), TransactionType.DEBIT.toString());
				
				transactionService.addTransaction(newTransaction);
				
				accountService.updateUser(user);
			}
		}
		
		// Updating accountBalance after credit
		
		@PostMapping("/credit/{accountNumber}")
		public User credit(@PathVariable String accountNumber, @RequestBody String amount) throws Exception{

			long userAccountNumber = Long.parseLong(accountNumber);

			long creditAmount = Long.parseLong(amount);

			User user = accountService.getUser(userAccountNumber);
				
			user.incrementAccountBalance(creditAmount) ;
				
			Transactions newTransaction = 
					new Transactions(userAccountNumber, Long.parseLong(amount), TransactionType.CREDIT.toString());
				
			transactionService.addTransaction(newTransaction);
				
			return accountService.updateUser(user);
				
		}
		
		// GetSummary for transactions in an account
		
		@GetMapping("/getSummary/{accountNumber}")
		
		public ArrayList < Transactions > transactionSummary(@PathVariable String accountNumber){
			
			return transactionService.getSummary(Long.parseLong(accountNumber));
		}
}
