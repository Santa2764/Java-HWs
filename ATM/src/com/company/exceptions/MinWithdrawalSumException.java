package com.company.exceptions;

public class MinWithdrawalSumException extends AtmException {
    private final int sum;

    public MinWithdrawalSumException(int sum) {
        super("The minimum withdrawal limit <" + sum + "> for the ATM...");
        this.sum = sum;
    }

    public int getSum() {
        return sum;
    }
}
