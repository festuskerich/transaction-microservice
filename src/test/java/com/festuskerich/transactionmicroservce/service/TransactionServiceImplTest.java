package com.festuskerich.transactionmicroservce.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.festuskerich.transactionmicroservce.dto.TransactionDto;
import com.festuskerich.transactionmicroservce.dto.TransactionUpdateDto;
import com.festuskerich.transactionmicroservce.entities.TransactionEntity;
import com.festuskerich.transactionmicroservce.repositories.TransactionRepository;
import com.festuskerich.transactionmicroservce.serviceImpl.TransactionServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.*;

class TransactionServiceImplTest {

    private TransactionRepository repository;
    @MockBean
    private ModelMapper modelMapper;
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        repository = mock(TransactionRepository.class);
        modelMapper = mock(ModelMapper.class);
        transactionService = new TransactionServiceImpl(repository, modelMapper);
    }

    @Test
    void testCreateTransaction() {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAmount(10);
        transactionDto.setReceiverName("Kerich");
        transactionDto.setSenderName("Kimtai");
        TransactionEntity entity = new TransactionEntity();
        TransactionDto responseDto = new TransactionDto();
        when(modelMapper.map(transactionDto, TransactionEntity.class)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(modelMapper.map(entity, TransactionDto.class)).thenReturn(responseDto);
        TransactionDto result = transactionService.createTransaction(transactionDto);
        verify(modelMapper).map(transactionDto,TransactionEntity.class);
        verify(repository).save(entity);
        verify(modelMapper).map(entity, TransactionDto.class);
        assertEquals(responseDto, result);
    }

    @Test
    void testDeleteTransaction() {
        Long id = 1L;
        transactionService.deleteTransaction(id);
        verify(repository).deleteById(id);
    }

    @Test
    void testGetTransactionById() {
        Long id = 1L;
        TransactionEntity entity = new TransactionEntity();
        TransactionDto responseDto = new TransactionDto();
        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(modelMapper.map(entity, TransactionDto.class)).thenReturn(responseDto);
        TransactionDto result = transactionService.getTransactionById(id);
        verify(repository).findById(id);
        verify(modelMapper).map(entity, TransactionDto.class);
        assertEquals(responseDto, result);
    }

    @Test
    void testUpdateTransaction() {
        Long id = 1L;
        TransactionUpdateDto request = new TransactionUpdateDto();
        request.setSenderName("Kimtai");
        TransactionEntity dbTransaction = new TransactionEntity();
        dbTransaction.setAmount(10);
        dbTransaction.setReceiverName("Kerich");
        dbTransaction.setSenderName("Kimtai");
        TransactionDto responseDto = new TransactionDto();
        responseDto.setAmount(10);
        responseDto.setReceiverName("Kerich");
        responseDto.setSenderName("Kimtai");
        when(repository.findById(id)).thenReturn(Optional.of(dbTransaction));
        when(modelMapper.map(request, TransactionEntity.class)).thenReturn(dbTransaction);
        when(repository.save(dbTransaction)).thenReturn(dbTransaction);
        when(modelMapper.map(dbTransaction, TransactionDto.class)).thenReturn(responseDto);
        TransactionDto result = transactionService.updateTransaction(id, request);
        verify(repository).findById(id);
        verify(modelMapper).map(request, dbTransaction);
        verify(repository).save(dbTransaction);
        verify(modelMapper).map(dbTransaction, TransactionDto.class);
        assertEquals(responseDto, result);
    }
}