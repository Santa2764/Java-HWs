package com.company.exceptions;

public class MaxCountBanknotesException extends AtmException {
    private final int maxLimit;

    public MaxCountBanknotesException(int maxLimit) {
        super("The limit <" + maxLimit + "> the number of banknotes exceeds the maximum limit...");
        this.maxLimit = maxLimit;
    }

    public int getMaxLimit() {
        return maxLimit;
    }
}
