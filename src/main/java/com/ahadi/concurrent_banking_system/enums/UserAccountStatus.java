package com.ahadi.concurrent_banking_system.enums;

public enum UserAccountStatus {
    ACTIVE(0){
        @Override
        public String getTitle() {
            return "user.account.status.active";
        }
    },
    DE_ACTIVE(1){
        @Override
        public String getTitle() {
            return "user.account.status.de.active";
        }
    },
    PENDING(2){
        @Override
        public String getTitle() {
            return "user.account.status.pending";
        }
    };

    private final int value;
    private String title;

    UserAccountStatus(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }

    public abstract String getTitle();
}
