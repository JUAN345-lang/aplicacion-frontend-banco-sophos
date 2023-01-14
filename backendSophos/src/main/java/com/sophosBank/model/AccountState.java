package com.sophosBank.model;

public enum AccountState {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE"),
    CANCELED("CANCELED");

    private  final  String state;
    AccountState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
