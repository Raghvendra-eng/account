package com.user.account.services;

import java.util.List;
import com.user.account.advice.CustomExceptions;
import com.user.account.entity.Transactions;
import com.user.account.entity.User;
import org.springframework.http.ResponseEntity;
import javax.transaction.Transactional;

@Transactional
public interface TransactionService {
    Transactions addTransaction(Transactions newTransaction);
    List<Transactions> getSummary(Long accountNumber) throws CustomExceptions;
    ResponseEntity<String> debitService(User user, Long amount);
    ResponseEntity<String> creditService(User user, Long amount);
    ResponseEntity<String> fundTransferService(User debitedUser, User creditedUser,Long amount);
}
