package com.ahadi.concurrent_banking_system.config.core;

import lombok.*;
import lombok.experimental.SuperBuilder;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BaseModel {

    private Long id;

    private Long insertDate;

    private String userCreator;

    private Long updateDate;

    private String updatingUser;
}
