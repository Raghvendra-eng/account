package com.user.account.controller;

import java.util.List;

import com.user.account.advice.CustomExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.user.account.entity.User;
import com.user.account.message.DefaultMessage;
import com.user.account.entity.Transactions;
import com.user.account.services.AccountService;
import com.user.account.services.TransactionService;

import javax.validation.constraints.Min;

@Validated
@RestController
@RequestMapping("/")
public class AllControlers {


    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    // Updating accountBalance after debit

    @PostMapping("/debit/{accountNumber}")
    public ResponseEntity<String> debit(@PathVariable @Min(1) Long accountNumber, @RequestParam @Min(1) Long amount) throws CustomExceptions {
        User user = accountService.getUser(accountNumber);
        if ( amount.compareTo(user.getAccountBalance()) <= 0){
            user.incrementAccountBalance(-amount);
            Transactions newTransaction =
                    new Transactions(accountNumber, amount, DefaultMessage.DEBIT);
            transactionService.addTransaction(newTransaction);
            accountService.updateUser(user);
            return new ResponseEntity<>(DefaultMessage.TRANSACTION_SUCCESS, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(DefaultMessage.TRANSACTION_FAILED_LOW_BALANCE, HttpStatus.BAD_REQUEST);
        }
    }

    // Updating accountBalance after credit

    @PostMapping("/credit/{accountNumber}")
    public ResponseEntity<String> credit(@PathVariable @Min(1) Long accountNumber, @RequestParam @Min(1) Long amount) throws CustomExceptions {
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

    public List< Transactions > transactionSummary(@PathVariable @Min(1) Long accountNumber) throws CustomExceptions {
        return transactionService.getSummary(accountNumber);
    }
}
