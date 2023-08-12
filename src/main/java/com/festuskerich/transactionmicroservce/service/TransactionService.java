package com.festuskerich.transactionmicroservce.service;

import org.springframework.data.domain.Pageable;

import com.festuskerich.transactionmicroservce.dto.PaginatedResponse;
import com.festuskerich.transactionmicroservce.dto.TransactionDto;
import com.festuskerich.transactionmicroservce.dto.TransactionUpdateDto;

public interface TransactionService {
    TransactionDto createTransaction(TransactionDto transactionDto);
    void deleteTransaction(Long id);
    TransactionDto getTransactionById(Long id);
      PaginatedResponse<TransactionDto> getAllTransactions(Pageable pageable);
      TransactionDto updateTransaction(Long id, TransactionUpdateDto request);
}
