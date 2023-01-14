package com.sophosBank.model;

public enum AccountType {
    CORRIENTE("CUENTA_CORRIENTE"),
    AHORROS("CUENTA_AHORROS");

    private final String accountType;
    AccountType(String value) {
        this.accountType = value;
    }

    public String getAccountType() {
        return accountType;
    }
}
