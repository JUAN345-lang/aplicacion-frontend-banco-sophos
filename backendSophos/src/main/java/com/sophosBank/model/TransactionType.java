package com.sophosBank.model;

public enum TransactionType {
    CONSIGNACION("CONSIGNACION"),
    RETIRO("RETIRO"),
    TRANSFERENCIA("TRANSFERENCIA"),
    GMF("GMF");


    private final String transactionType;
    TransactionType(String value) {
        this.transactionType = value;
    }

    public String getTransactionType() {
        return transactionType;
    }
}
