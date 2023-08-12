package com.festuskerich.transactionmicroservce.dto;

import java.util.List;

import org.springframework.data.domain.Sort;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PaginatedResponse<T> {
    private List<T> items;
    private int totalPages;
    private long totalItems;
    private boolean last;
    private boolean first;
    private int numberOfElements;
    private int size;
    private int number;
    private Sort sort; 
}
