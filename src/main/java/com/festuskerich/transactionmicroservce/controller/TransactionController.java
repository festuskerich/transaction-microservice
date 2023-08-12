package com.festuskerich.transactionmicroservce.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.festuskerich.transactionmicroservce.dto.ApiResponse;
import com.festuskerich.transactionmicroservce.dto.PaginatedResponse;
import com.festuskerich.transactionmicroservce.dto.TransactionDto;
import com.festuskerich.transactionmicroservce.dto.TransactionUpdateDto;
import com.festuskerich.transactionmicroservce.service.TransactionService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/transactions")
@Slf4j
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/")
    
    public ResponseEntity<ApiResponse<TransactionDto>> createTransaction(@Valid @RequestBody TransactionDto request) {
        log.info("*****{}**** New transactions received for processing {}", request.getRequestID(), request);
        ApiResponse<TransactionDto> response = new ApiResponse<>(200, "Transaction saved successfully",
                transactionService.createTransaction(request));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TransactionDto>> updateTransaction(@PathVariable Long id,
            @RequestBody TransactionUpdateDto request) {
        log.info("*****{}**** Request to update transaction {}", request.getRequestID(), request);
        ApiResponse<TransactionDto> response = new ApiResponse<>(200, "Transaction updated successfully",
                transactionService.updateTransaction(id,request));
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PaginatedResponse<TransactionDto>>> getAllTransactions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        log.info(" New request to fetch all transactions");
        Pageable pageable = PageRequest.of(page, size);
        PaginatedResponse<TransactionDto> entitiesPage = transactionService.getAllTransactions(pageable);
        ApiResponse<PaginatedResponse<TransactionDto>> response = new ApiResponse<>(200,
                "Transaction saved successfully",
                entitiesPage);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TransactionDto>> getTransactionById(@PathVariable Long id) {
        log.info("*****{}**** New transaction query request received for processing {}", id);
        ApiResponse<TransactionDto> response = new ApiResponse<>(200, "Transaction",
                transactionService.getTransactionById(id));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        log.info("*****{}**** deleting transaction with ID {} ", id);
        transactionService.deleteTransaction(id);
        ApiResponse<Void> response = new ApiResponse<>(204, "Transaction deleted successfully", null);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

}
