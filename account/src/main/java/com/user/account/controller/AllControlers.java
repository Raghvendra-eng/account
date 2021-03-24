package com.user.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.user.account.entity.User;
import com.user.account.services.AccountServices;



@RestController
public class AllControlers {
	
	
		@Autowired
		private AccountServices accountServices; 
		
		// tomcat test
		@GetMapping("/home")
		public String home() {
			return "This is the home page";
		}
		
		// Updating accountBalance after debit
		
		
		@PostMapping("/debit/{accountNumber}/{amount}")
		public User debit(@PathVariable String accountNumber, @PathVariable String amount) {
			
			User user = this.accountServices.getUser(Long.parseLong(accountNumber));
			
			if ( Long.parseLong(amount) <= user.getAccountBalance() ) {
				
				user.updateBalanceBy(-Long.parseLong(amount)) ;
				
				return this.accountServices.updateUser(user);
			}
			
			else 
				return user;
		}
		
		// Updating accountBalance after credit
		
		@PostMapping("/credit/{accountNumber}/{amount}")
		public User credit(@PathVariable String accountNumber, @PathVariable String amount) {
				
			User user = this.accountServices.getUser(Long.parseLong(accountNumber));
			
			if ( Long.parseLong(amount) <= user.getAccountBalance() ) {
				
				user.updateBalanceBy(Long.parseLong(amount)) ;
				
				return this.accountServices.updateUser(user);
			}
			
			else 
				return user;

		}
}
