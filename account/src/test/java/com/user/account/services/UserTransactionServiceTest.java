package com.user.account.services;

import com.user.account.entity.Transactions;
import com.user.account.repositories.TransactionRepository;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserTransactionServiceTest {
    @Mock
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionService transactionService;

    @Test
    public void addTransactionTest() throws Exception{
        Transactions inputTransactions = new Transactions(2, 1200, "Credit");
        Mockito.when(transactionRepository.save(inputTransactions)).thenReturn(inputTransactions);
        assertEquals(inputTransactions, transactionRepository.save(inputTransactions));
    }

    @Test
    public void getTransactionSummaryTest() throws Exception{
        List <Transactions> userTransactionSummary = new ArrayList<>();
        userTransactionSummary.add(new Transactions(2, 1200,"Credit"));
        userTransactionSummary.add(new Transactions(2, 1300,"Credit"));
        Mockito.when(transactionRepository.findByAccountNumber(2L)).thenReturn(userTransactionSummary);
        assertEquals(userTransactionSummary, transactionRepository.findByAccountNumber(2L));
    }

    @After
    public void tearDown() {
        verifyNoMoreInteractions(transactionRepository);
    }
}
