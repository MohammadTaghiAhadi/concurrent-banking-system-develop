package com.ahadi.concurrent_banking_system.model;


import com.ahadi.concurrent_banking_system.config.core.BaseModel;
import com.ahadi.concurrent_banking_system.enums.TransactionStatus;
import com.ahadi.concurrent_banking_system.enums.TransactionType;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter@Setter@SuperBuilder@ToString@NoArgsConstructor
public class TransactionModel extends BaseModel {


    private Double amount;

    private TransactionType transactionType;

    private TransactionStatus transactionStatus;

    private Long userAccountId;

    private String userAccountNumber;

    private String accountHolderName;
}
