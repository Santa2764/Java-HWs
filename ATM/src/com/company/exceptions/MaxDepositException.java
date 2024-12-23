package com.company.exceptions;

public class MaxDepositException  extends AtmException {
    private final int limit;

    public MaxDepositException(int limit) {
        super("The maximum deposit limit <" + limit + "> for the ATM has been exceeded...");
        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }
}
