package com.ahadi.concurrent_banking_system.desing_pattern.observer;

public interface EventListener {
    void update(Long accountNumber,String transactionType,Double amount);
}