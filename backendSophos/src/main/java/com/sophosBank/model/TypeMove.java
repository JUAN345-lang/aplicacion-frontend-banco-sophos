package com.sophosBank.model;

import java.lang.reflect.Type;

public enum TypeMove {
    DEBITO("DEBITO"),
    CREDITO("CREDITO");

    private final String TypeMove;
    TypeMove(String moveType) {
        this.TypeMove = moveType;
    }

    public String getTypeMove() {
        return TypeMove;
    }
}
