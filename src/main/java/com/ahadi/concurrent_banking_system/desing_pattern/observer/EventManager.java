package com.ahadi.concurrent_banking_system.desing_pattern.observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class EventManager {

    Map<String, List<EventListener>> listeners = new HashMap<>();

    public EventManager(String... operations) {
        for (String operation : operations) {
            this.listeners.put(operation, new ArrayList<>());
        }
    }

    public void subscribe(String eventType, EventListener listener) {
        List<EventListener> users = listeners.get(eventType);
        users.add(listener);
    }

    public void notify(Long accountId,String transactionType, Double amount) {
        List<EventListener> users = listeners.get(transactionType);
        for (EventListener listener : users) {
            listener.update(accountId, transactionType, amount);
        }
    }
}
