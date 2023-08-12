package com.festuskerich.transactionmicroservce.dto;

import java.util.Date;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TransactionDto {
    @NotNull(message = "Amount may not be blank")
    @Max(150000)
    @Min(5) 
    Integer amount;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
     @NotNull(message = "Date may not be blank")
    Date transactionDate;
     @NotNull(message = "Sender name may not be blank")
    String senderName;
     @NotNull(message = "Receiver name may not be blank")
    String receiverName;
}
