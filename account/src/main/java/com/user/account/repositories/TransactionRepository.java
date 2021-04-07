package com.user.account.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.account.entity.Transactions;

public interface TransactionRepository extends JpaRepository<Transactions, Long>{
    public List< Transactions> findByAccountNumber(Long accountNumber);
}
