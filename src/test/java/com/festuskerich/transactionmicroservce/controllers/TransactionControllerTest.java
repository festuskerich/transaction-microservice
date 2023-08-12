package com.festuskerich.transactionmicroservce.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.festuskerich.transactionmicroservce.controller.TransactionController;
import com.festuskerich.transactionmicroservce.dto.ApiResponse;
import com.festuskerich.transactionmicroservce.dto.TransactionDto;
import com.festuskerich.transactionmicroservce.dto.TransactionUpdateDto;
import com.festuskerich.transactionmicroservce.service.TransactionService;

class TransactionControllerTest {
    @MockBean
    private TransactionService transactionService;
    private TransactionController transactionController;

    @BeforeEach
    void setUp() {
        transactionService = mock(TransactionService.class);
        transactionController = new TransactionController(transactionService);
    }

    @Test
    void testCreateTransaction() {
        TransactionDto requestDto = new TransactionDto();
        requestDto.setAmount(10);
        requestDto.setReceiverName("Kerich");
        requestDto.setSenderName("Mika");
        TransactionDto responseDto = new TransactionDto();
        when(transactionService.createTransaction(any(TransactionDto.class))).thenReturn(responseDto);
        ResponseEntity<ApiResponse<TransactionDto>> responseEntity = transactionController
                .createTransaction(requestDto);
        verify(transactionService).createTransaction(requestDto);
        ApiResponse<TransactionDto> responseBody = responseEntity.getBody();
        assert responseBody != null;
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(200, responseBody.status());
        assertEquals("Transaction saved successfully", responseBody.message());
        assertEquals(responseDto, responseBody.data());
    }

    @Test
    void testUpdateTransaction() {
        Long id = 1L;
        TransactionUpdateDto requestDto = new TransactionUpdateDto();
        requestDto.setSenderName("Kimtai");
        TransactionDto responseDto = new TransactionDto();
        responseDto.setAmount(10);
        responseDto.setReceiverName("Kerich");
        responseDto.setSenderName("Kimtai");
        when(transactionService.updateTransaction(eq(id), any(TransactionUpdateDto.class))).thenReturn(responseDto);
        ResponseEntity<ApiResponse<TransactionDto>> responseEntity = transactionController.updateTransaction(id,
                requestDto);
        verify(transactionService).updateTransaction(eq(id), eq(requestDto));
        ApiResponse<TransactionDto> responseBody = responseEntity.getBody();
        assert responseBody != null;
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(200, responseBody.status());
        assertEquals("Transaction updated successfully", responseBody.message());
        assertEquals(responseDto, responseBody.data());
    }

    @Test
    void testGetTransactionById() {
        Long id = 1L;
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAmount(10);
        transactionDto.setReceiverName("Kerich");
        transactionDto.setSenderName("Mika");
        when(transactionService.getTransactionById(id)).thenReturn(transactionDto);
        ResponseEntity<ApiResponse<TransactionDto>> responseEntity = transactionController.getTransactionById(id);
        verify(transactionService).getTransactionById(id);
        ApiResponse<TransactionDto> responseBody = responseEntity.getBody();
        assert responseBody != null;
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(200, responseBody.status());
        assertEquals("Transaction", responseBody.message());
        assertEquals(transactionDto, responseBody.data());
    }

    @Test
    void testDelete() {
        Long id = 1L;
        ResponseEntity<ApiResponse<Void>> responseEntity = transactionController.delete(id);
        verify(transactionService).deleteTransaction(id);
        ApiResponse<Void> responseBody = responseEntity.getBody();
        assert responseBody != null;
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        assertEquals(204, responseBody.status());
        assertEquals("Transaction deleted successfully", responseBody.message());
        assertNull(responseBody.data());
    }
}
