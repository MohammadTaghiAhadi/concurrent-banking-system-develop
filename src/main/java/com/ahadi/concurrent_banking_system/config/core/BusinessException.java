package com.ahadi.concurrent_banking_system.config.core;


public class BusinessException extends Exception {

    public BusinessException(String errorMessage) {
        super(errorMessage);
    }
}
