package com.ahadi.concurrent_banking_system.config.core;


import lombok.*;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ApiValidationError extends ApiSubError{

    private String object;
    private String field;
    private Object rejectedValue;
    private String message;

    public ApiValidationError() {
    }

    public ApiValidationError(String object, String message) {
        this.object = object;
        this.message = message;
    }
}
