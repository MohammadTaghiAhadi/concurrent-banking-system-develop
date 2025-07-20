package com.ahadi.concurrent_banking_system.enums;

public enum TransactionType {
    DEPOSIT(0){
        @Override
        public String getTitle() {
            return "transaction.type.deposit";
        }
    },
    WITHDRAWAL(1){
        @Override
        public String getTitle() {
            return "transaction.type.withdrawal";
        }
    };

    private final int value;
    private String title;

    TransactionType(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }

    public abstract String getTitle();
}
