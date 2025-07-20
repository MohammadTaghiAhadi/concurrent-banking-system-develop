package com.ahadi.concurrent_banking_system.model;


import com.ahadi.concurrent_banking_system.config.core.BaseModel;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter@Setter@NoArgsConstructor@ToString@SuperBuilder
public class TransferAmountModel extends BaseModel {

    private Double amount;

    private Long depositorId;
    private String depositorHolderName;
    private Long depositorAccountNumber;

    private Long withdrawalId;
    private String withdrawerHolderName;
    private Long withdrawerAccountNumber;
}
