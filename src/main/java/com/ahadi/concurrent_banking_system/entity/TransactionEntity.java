package com.ahadi.concurrent_banking_system.entity;


import com.ahadi.concurrent_banking_system.config.core.BaseEntity;
import com.ahadi.concurrent_banking_system.enums.TransactionStatus;
import com.ahadi.concurrent_banking_system.enums.TransactionType;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "TBL_TRANSACTION")
@Getter@Setter@ToString
@SuperBuilder
@NoArgsConstructor
public class TransactionEntity extends BaseEntity {


    @Column(name="AMOUNT",nullable = false)
    @NotNull(message = "amount.must.be.not.null")
    private Double amount;

    @Column(name="TRANSACTION_TYPE")
    @Enumerated(value = EnumType.ORDINAL)
    private TransactionType transactionType;

    @Column(name="TRANSACTION_STATUS")
    @Enumerated(value = EnumType.ORDINAL)
    private TransactionStatus transactionStatus;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ACCOUNT_ID",nullable = false,updatable = false)
    private UserAccountEntity userAccount;
}
