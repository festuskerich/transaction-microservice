package com.festuskerich.transactionmicroservce.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.festuskerich.transactionmicroservce.dto.PaginatedResponse;
import com.festuskerich.transactionmicroservce.dto.TransactionDto;
import com.festuskerich.transactionmicroservce.dto.TransactionUpdateDto;
import com.festuskerich.transactionmicroservce.entities.TransactionEntity;
import com.festuskerich.transactionmicroservce.repositories.TransactionRepository;
import com.festuskerich.transactionmicroservce.service.TransactionService;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repository;
    private final ModelMapper modelMapper;

    public TransactionServiceImpl(TransactionRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public ModelMapper getModelMapper() {
        return modelMapper;
    }

    @Override
    public TransactionDto createTransaction(TransactionDto transactionDto) {
         TransactionEntity entity=modelMapper.map(transactionDto, TransactionEntity.class);
        log.info("About to save transaction {} in the database", transactionDto);
        var saved = repository.save(entity);
        return modelMapper.map(saved, TransactionDto.class);
    }

    @Override
    public void deleteTransaction(Long id) {
        repository.deleteById(id);
    }

    @Override
    public TransactionDto getTransactionById(Long id) {
        TransactionEntity entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity with id " + id + " not found"));
        return modelMapper.map(entity, TransactionDto.class);
    }

    @Override
    public PaginatedResponse<TransactionDto> getAllTransactions(Pageable pageable) {
        var transactionsPage = repository.findAll(pageable);
        List<TransactionDto> transactionDtos = transactionsPage.getContent().stream()
                .map(entity -> modelMapper.map(entity, TransactionDto.class))
                .collect(Collectors.toList());
        PaginatedResponse<TransactionDto> response = new PaginatedResponse<>();
        response.setItems(transactionDtos);
        response.setTotalPages(transactionsPage.getTotalPages());
        response.setTotalItems(transactionsPage.getTotalElements());
        response.setLast(transactionsPage.isLast());
        response.setFirst(transactionsPage.isFirst());
        response.setNumberOfElements(transactionsPage.getNumberOfElements());
        response.setSize(transactionsPage.getSize());
        response.setNumber(transactionsPage.getNumber());
        response.setSort(transactionsPage.getSort());
        return response;

    }

    @Override
    public TransactionDto updateTransaction(Long id, TransactionUpdateDto request) {
        TransactionEntity dbTransaction = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity with id " + id + " not found"));
        modelMapper.map(request, dbTransaction);
        var updated =repository.save(dbTransaction);
        return modelMapper.map(updated, TransactionDto.class);

    }

}
