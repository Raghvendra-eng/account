package com.user.account.services;

import java.util.List;
import com.user.account.advice.UserNotFoundException;
import com.user.account.entity.Transactions;
import com.user.account.entity.User;
import javax.transaction.Transactional;

@Transactional
public interface TransactionService {
    Transactions addTransaction(Transactions newTransaction);
    List<Transactions> getSummary(Long accountNumber) throws UserNotFoundException;
    boolean debitService(User user, Long amount);
    boolean creditService(User user, Long amount);
    boolean fundTransferService(User debitedUser, User creditedUser,Long amount);
}
