package com.user.account.services;

import java.util.List;

import com.user.account.entity.Transactions;

public interface TransactionService {

    public Transactions addTransaction(Transactions newTransaction);

    public List<Transactions> getSummary(Long accountNumber);
}
