package com.user.account.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.user.account.services.AccountService;
import com.user.account.services.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;


import com.user.account.entity.Transactions;
import com.user.account.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class AllControllersTest {

    @Mock
    private AccountService accountServices;

    @Mock
    private TransactionService transactionServices;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testDebit() throws Exception{

		User inputUser = new User(2, 1200, "Shyam");
		User outputUser = new User(2, 1100, "Shyam");
		Transactions inputTransaction = new Transactions(1,1,100, "debit");
        String uri = UriComponentsBuilder.newInstance().path("/debit/2")
                .build().toUri().toString();
        //{accountNumber}
		Mockito.when(accountServices.getUser(2L)).thenReturn(inputUser);
		Mockito.when(accountServices.updateUser(inputUser)).thenReturn(outputUser);
		Mockito.when(transactionServices.addTransaction(inputTransaction)).thenReturn(inputTransaction);
		mockMvc.perform(post(uri).param("amount","100")).andExpect(status().isOk());
    }


	@Test
	public void testCredit() throws Exception{

		User inputUser = new User(2, 1200, "Shyam");
		User outputUser = new User(2, 1300, "Shyam");
		Transactions inputTransaction = new Transactions(2,100, "credit");
		String url = "/credit/2";
		Mockito.when(accountServices.getUser(2L)).thenReturn(inputUser);
		Mockito.when(accountServices.updateUser(inputUser)).thenReturn(outputUser);
		Mockito.when(transactionServices.addTransaction(inputTransaction)).thenReturn(inputTransaction);
		mockMvc.perform(post(url).param("amount","100")).andExpect(status().isOk());

	}

	@Test
	public void testGetSummary() throws Exception{
		List<Transactions> userTransactionSummary = new ArrayList<>();
		Transactions transactionEntity = new Transactions(2, 1200, "Credit");
		Transactions anotherTransactionEntity = new Transactions(2, 1200, "Debit");
		userTransactionSummary.add(transactionEntity);
		userTransactionSummary.add(anotherTransactionEntity);
		String url = "/getSummary/2";
		mockMvc.perform(get(url)).andExpect(status().isOk());

	}
}
