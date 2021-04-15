package com.user.account.services.serviceImplementation;

import java.util.List;
import com.user.account.advice.CustomExceptions;
import com.user.account.entity.User;
import com.user.account.message.DefaultMessage;
import com.user.account.services.AccountService;
import com.user.account.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.user.account.entity.Transactions;
import com.user.account.repositories.TransactionRepository;
import javax.transaction.Transactional;

@Service
public class UserTransactionService implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    public ResponseEntity<String> debitImplementation(User user, Long amount){
        if ( amount.compareTo(user.getAccountBalance()) <= 0){
            user.decrementAccountBalance(amount);
            Transactions newTransaction =
                    new Transactions(user.getAccountNumber(), amount, DefaultMessage.DEBIT);
            addTransaction(newTransaction);
            accountService.updateUser(user);
            return new ResponseEntity<>(DefaultMessage.TRANSACTION_SUCCESS, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(DefaultMessage.TRANSACTION_FAILED_LOW_BALANCE, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> creditImplementation(User user, Long amount){
        user.incrementAccountBalance(amount) ;
        Transactions newTransaction =
                new Transactions(user.getAccountNumber(), amount, DefaultMessage.CREDIT);
        addTransaction(newTransaction);
        accountService.updateUser(user);
        return new ResponseEntity<>(DefaultMessage.TRANSACTION_SUCCESS, HttpStatus.OK);
    }

    @Override
    @Transactional
    public Transactions addTransaction(Transactions newTransaction) {
        return transactionRepository.save(newTransaction);
    }

    @Override
    public List<Transactions> getSummary(Long accountNumber) throws CustomExceptions {
        if(!transactionRepository.existsById(accountNumber))
            throw new CustomExceptions(DefaultMessage.INVALID_ACCOUNT_NUMBER);
        return transactionRepository.findByAccountNumber(accountNumber);
    }

    @Override
    public ResponseEntity<String> debitService(User user, Long amount){
        return debitImplementation(user, amount);
    }

    @Override
    public ResponseEntity<String> creditService(User user, Long amount){
        return creditImplementation(user, amount);
    }

    @Override
    public ResponseEntity<String> fundTransferService(User debitedUser, User creditedUser, Long amount){
        ResponseEntity<String> debitResponse = debitImplementation(debitedUser, amount);
        ResponseEntity<String> returnResponse = new ResponseEntity<>(DefaultMessage.TRANSACTION_SUCCESS, HttpStatus.OK);
        ResponseEntity<String> creditResponse;
        if(!debitResponse.equals(returnResponse))
            return debitResponse;
        creditResponse = creditImplementation(creditedUser, amount);
        if(!creditResponse.equals(returnResponse))
            return creditResponse;
        return returnResponse;
    }

}
