package com.company.exceptions;

public class MinDepositException extends AtmException {
    private final int limit;

    public MinDepositException(int limit) {
        super("The minimum deposit limit <" + limit + "> for the ATM...");
        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }
}
