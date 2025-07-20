package com.ahadi.concurrent_banking_system.model;


import com.ahadi.concurrent_banking_system.config.core.BaseModel;
import com.ahadi.concurrent_banking_system.enums.UserAccountStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter@Setter@NoArgsConstructor@ToString@SuperBuilder
public class UserAccountModel extends BaseModel {

    private String accountNumber;

    private String holderName;

    private Double balance;

    private UserAccountStatus status;

}
