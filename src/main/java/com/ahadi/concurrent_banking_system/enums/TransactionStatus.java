package com.ahadi.concurrent_banking_system.enums;

public enum TransactionStatus {
    SUCCESS(0){
        @Override
        public String getTitle() {
            return "transaction.status.success";
        }
    },
    FAILED(1){
        @Override
        public String getTitle() {
            return "transaction.status.failed";
        }
    };

    private final int value;
    private String title;

    TransactionStatus(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }

    public abstract String getTitle();
}
