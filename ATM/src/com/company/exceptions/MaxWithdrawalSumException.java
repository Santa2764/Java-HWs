package com.company.exceptions;

public class MaxWithdrawalSumException extends AtmException {
    private final int sum;

    public MaxWithdrawalSumException(int sum) {
        super("The maximum withdrawal limit <" + sum + "> for the ATM...");
        this.sum = sum;
    }

    public int getSum() {
        return sum;
    }
}
