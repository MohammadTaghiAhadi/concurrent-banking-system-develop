package com.ahadi.concurrent_banking_system.desing_pattern.observer;

import com.ahadi.concurrent_banking_system.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionLogger implements EventListener {

    @Autowired
    Util util;
    @Override
    public void update(Long accountId, String transactionType, Double amount) {
        util.createTextFile(accountId+"," + transactionType+"," + amount+"\n");
    }
}
