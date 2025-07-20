package com.ahadi.concurrent_banking_system.entity;


import com.ahadi.concurrent_banking_system.config.core.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "TBL_TRANSFER_AMOUNT")
@Getter@Setter@ToString@SuperBuilder@NoArgsConstructor
public class TransferAmountEntity extends BaseEntity {


    @Column(name="AMOUNT",nullable = false)
    @NotNull(message = "amount.must.be.not.null")
    private Double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPOSITOR_ID",nullable = false,updatable = false)
    private UserAccountEntity depositor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WITHDRAWER_ID",nullable = false,updatable = false)
    private UserAccountEntity withdrawer;
}
