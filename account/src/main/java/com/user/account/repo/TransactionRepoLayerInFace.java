package com.user.account.repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.user.account.entity.Transactions;

public interface TransactionRepoLayerInFace extends JpaRepository<Transactions, Long>{
	@Query
	public ArrayList< Transactions> findByAccountNumber(long accountNumber);
}
