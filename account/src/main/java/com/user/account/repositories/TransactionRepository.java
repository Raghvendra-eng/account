package com.user.account.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.user.account.entity.Transactions;

public interface TransactionRepository extends JpaRepository<Transactions, Long>{
	@Query
	public ArrayList< Transactions> findByAccountNumber(Long accountNumber);
}
