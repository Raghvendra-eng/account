package com.user.account.services;

import java.util.List;

import com.user.account.advice.CustomExceptions;
import com.user.account.message.DefaultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.account.entity.Transactions;
import com.user.account.repositories.TransactionRepository;

import javax.transaction.Transactional;


@Service
public class UserTransactionService implements TransactionService {


    @Autowired
    private TransactionRepository transactionRepository;

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

}
