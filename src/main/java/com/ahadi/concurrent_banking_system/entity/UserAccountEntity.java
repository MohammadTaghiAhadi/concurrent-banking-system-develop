package com.ahadi.concurrent_banking_system.entity;


import com.ahadi.concurrent_banking_system.config.core.BaseEntity;
import com.ahadi.concurrent_banking_system.enums.UserAccountStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "TBL_USER_ACCOUNT")
@Getter@Setter@ToString@SuperBuilder
@NoArgsConstructor
public class UserAccountEntity extends BaseEntity {

    @NotBlank(message = "account.number.must.be.not.null")
    @Column(name="ACOOUNT_NUMBER",nullable = false)
    private String accountNumber;

    @NotBlank(message = "holder.name.must.be.not.blank")
    @Column(name="HOLDER_NAME",nullable = false)
    private String holderName;

    @Column(name="BALANCE",nullable = false)
    @Min(value = 0,message = "balance.must.be.greater.than.zero")
    @NotNull(message = "balance.must.be.not.null")
    private Double balance;

    @Column(name="STATUS")
    @Enumerated(value = EnumType.ORDINAL)
    private UserAccountStatus status;

}
