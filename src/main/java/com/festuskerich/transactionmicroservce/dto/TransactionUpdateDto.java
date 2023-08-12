package com.festuskerich.transactionmicroservce.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class TransactionUpdateDto {
    Integer amount;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
    Date transactionDate;
    String senderName;
    String receiverName;

}
