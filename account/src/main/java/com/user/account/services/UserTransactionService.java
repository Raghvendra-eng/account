package com.user.account.services;

import java.util.List;

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
    public List<Transactions> getSummary(Long accountNumber) {
        return transactionRepository.findByAccountNumber(accountNumber);
    }

}
