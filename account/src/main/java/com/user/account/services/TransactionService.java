package com.user.account.services;

import java.util.List;

import com.user.account.advice.CustomExceptions;
import com.user.account.entity.Transactions;

public interface TransactionService {

    Transactions addTransaction(Transactions newTransaction);

    List<Transactions> getSummary(Long accountNumber) throws CustomExceptions;
}
