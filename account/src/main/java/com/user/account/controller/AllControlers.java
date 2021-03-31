package com.user.account.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.user.account.entity.User;
import com.user.account.message.DefaultMessage;
import com.user.account.entity.Transactions;
import com.user.account.services.AccountService;
import com.user.account.services.TransactionService;

@Validated
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
		public ResponseEntity<String> debit(@PathVariable Long accountNumber, @RequestBody Long amount) {
			
			User user = accountService.getUser(accountNumber);
			
			if ( amount.compareTo(user.getAccountBalance()) <= 0 ) {
				
				user.incrementAccountBalance(-amount) ;
				
				Transactions newTransaction = 
						new Transactions(accountNumber, amount, DefaultMessage.DEBIT);
				
				transactionService.addTransaction(newTransaction);
				
				accountService.updateUser(user);
				
				return new ResponseEntity<>(DefaultMessage.TRANSACTION_SUCCESS, HttpStatus.OK);
			}
			
			else {
				return new ResponseEntity<>(DefaultMessage.TRANSACTION_FAILED, HttpStatus.OK);
			}
		}
		
		// Updating accountBalance after credit
		
		@PostMapping("/credit/{accountNumber}")
		public ResponseEntity<String> credit(@PathVariable Long accountNumber, @RequestBody Long amount) throws Exception{

			User user = accountService.getUser(accountNumber);
				
			user.incrementAccountBalance(amount) ;
				
			Transactions newTransaction = 
					new Transactions(accountNumber, amount, DefaultMessage.CREDIT);
				
			transactionService.addTransaction(newTransaction);
				
			accountService.updateUser(user);
			
			return new ResponseEntity<>(DefaultMessage.TRANSACTION_SUCCESS, HttpStatus.OK);
				
		}
		
		// GetSummary for transactions in an account
		
		@GetMapping("/getSummary/{accountNumber}")
		
		public ArrayList < Transactions > transactionSummary(@PathVariable Long accountNumber){
			
			return transactionService.getSummary(accountNumber);
		}
}
