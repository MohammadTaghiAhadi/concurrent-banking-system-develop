package com.ahadi.concurrent_banking_system.desing_pattern.decorator.base;

import com.ahadi.concurrent_banking_system.enums.TransactionType;
import com.ahadi.concurrent_banking_system.desing_pattern.observer.EventManager;
import org.springframework.stereotype.Component;

@Component
public abstract class TransactionDecorator implements Transaction {

    public EventManager events;

    public TransactionDecorator() {
        this.events = new EventManager(TransactionType.DEPOSIT.getTitle(), TransactionType.WITHDRAWAL.getTitle());
    }

}
