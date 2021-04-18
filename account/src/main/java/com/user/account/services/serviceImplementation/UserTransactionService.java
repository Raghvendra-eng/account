package com.user.account.services.serviceImplementation;

import java.util.List;
import com.user.account.advice.UserNotFoundException;
import com.user.account.entity.User;
import com.user.account.message.DefaultMessage;
import com.user.account.services.AccountService;
import com.user.account.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    @Transactional
    public Transactions addTransaction(Transactions newTransaction) {
        return transactionRepository.save(newTransaction);
    }

    @Override
    public List<Transactions> getSummary(Long accountNumber) throws UserNotFoundException {
        if(!transactionRepository.existsById(accountNumber))
            throw new UserNotFoundException(DefaultMessage.INVALID_ACCOUNT_NUMBER);
        return transactionRepository.findByAccountNumber(accountNumber);
    }

    @Override
    public boolean debitService(User user, Long amount){
        if ( amount.compareTo(user.getAccountBalance()) <= 0){
            user.decrementAccountBalance(amount);
            Transactions newTransaction =
                    new Transactions(user.getAccountNumber(), amount, DefaultMessage.DEBIT);
            addTransaction(newTransaction);
            accountService.updateUser(user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean creditService(User user, Long amount){
        user.incrementAccountBalance(amount) ;
        Transactions newTransaction =
                new Transactions(user.getAccountNumber(), amount, DefaultMessage.CREDIT);
        addTransaction(newTransaction);
        accountService.updateUser(user);
        return true;
    }

    @Override
    public boolean fundTransferService(User debitedUser, User creditedUser, Long amount){
        boolean debitResponse = debitService(debitedUser, amount);
        if(!debitResponse)
            return false;
        return creditService(creditedUser, amount);
    }
}
