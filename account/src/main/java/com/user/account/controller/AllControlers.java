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

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.Min;

@Validated
@RestController
@RequestMapping("/api")
public class AllControlers {


    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    public ResponseEntity<String> CreditAndDebitRequest(Long accountNumber, String transactionAmount, String transactionType){
        try {
            User user = accountService.getUser(accountNumber);
            Long amount = Long.parseLong(transactionAmount);
            if(transactionType.equals(DefaultMessage.DEBIT) ) {
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
            else{
                user.incrementAccountBalance(amount) ;
                Transactions newTransaction =
                        new Transactions(accountNumber, amount, DefaultMessage.CREDIT);
                transactionService.addTransaction(newTransaction);
                accountService.updateUser(user);
                return new ResponseEntity<>(DefaultMessage.TRANSACTION_SUCCESS, HttpStatus.OK);
            }

        }
        catch (NumberFormatException exception)
        {
            return new ResponseEntity<>(DefaultMessage.TRANSACTION_FAILED_INVALID_AMOUNT, HttpStatus.BAD_REQUEST);
        }
        catch (EntityNotFoundException exception)
        {
            return new ResponseEntity<>(DefaultMessage.INVALID_ACCOUNT_NUMBER, HttpStatus.BAD_REQUEST);
        }
    }

    // Updating accountBalance after debit

    @PostMapping("/debit/{accountNumber}")
    public ResponseEntity<String> debit(@PathVariable @Min(1) Long accountNumber, @RequestBody String transactionAmount) {
        return CreditAndDebitRequest(accountNumber, transactionAmount, DefaultMessage.DEBIT);
    }

    // Updating accountBalance after credit

    @PostMapping("/credit/{accountNumber}")
    public ResponseEntity<String> credit(@PathVariable @Min(1) Long accountNumber, @RequestBody String transactionAmount) throws Exception{
        return CreditAndDebitRequest(accountNumber, transactionAmount, DefaultMessage.CREDIT);
    }

    // GetSummary for transactions in an account

    @GetMapping("/getSummary/{accountNumber}")

    public ArrayList < Transactions > transactionSummary(@PathVariable @Min(1) Long accountNumber){
        return transactionService.getSummary(accountNumber);
    }
}
