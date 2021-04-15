package com.user.account.controller;
import java.util.List;
import com.user.account.advice.CustomExceptions;
import com.user.account.entity.FundTransferDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.user.account.entity.User;
import com.user.account.entity.Transactions;
import com.user.account.services.AccountService;
import com.user.account.services.TransactionService;
import javax.validation.constraints.Min;

@Validated
@RestController
@RequestMapping("/")
public class AllControllers {

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    // Updating accountBalance after debit

    @PostMapping("/debit/{accountNumber}")
    public ResponseEntity<String> debit(@PathVariable @Min(1) Long accountNumber, @RequestParam @Min(1) Long amount) throws CustomExceptions {
        User user = accountService.getUser(accountNumber);
        return transactionService.debitService(user, amount);
    }

    // Updating accountBalance after credit

    @PostMapping("/credit/{accountNumber}")
    public ResponseEntity<String> credit(@PathVariable @Min(1) Long accountNumber, @RequestParam @Min(1) Long amount) throws CustomExceptions {
        User user = accountService.getUser(accountNumber);
        return transactionService.creditService(user, amount);
    }

    // GetSummary for transactions in an account

    @GetMapping("/getSummary/{accountNumber}")
    public List< Transactions > transactionSummary(@PathVariable @Min(1) Long accountNumber) throws CustomExceptions {
        return transactionService.getSummary(accountNumber);
    }

    // Fund Transfer from one Account to another

    @PostMapping("/transaction")
    public ResponseEntity<String> fundTransfer(@RequestBody FundTransferDetails fundTransferDetails) throws CustomExceptions {
        User debitedUser = accountService.getUser(fundTransferDetails.getDebitUserAccountNumber());
        User creditedUser = accountService.getUser(fundTransferDetails.getCreditUserAccountNumber());
        return transactionService.fundTransferService(debitedUser, creditedUser, fundTransferDetails.getTransactionAmount());
    }
}
