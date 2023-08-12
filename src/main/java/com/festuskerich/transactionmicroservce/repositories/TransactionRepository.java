package com.festuskerich.transactionmicroservce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.festuskerich.transactionmicroservce.entities.TransactionEntity;

public interface TransactionRepository extends JpaRepository<TransactionEntity,Long>{
    
}
