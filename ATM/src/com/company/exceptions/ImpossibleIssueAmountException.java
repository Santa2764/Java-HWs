package com.company.exceptions;

public class ImpossibleIssueAmountException extends AtmException {
    private final int sum;

    public ImpossibleIssueAmountException(int sum) {
        super("It is impossible to issue the specified amount <" + sum + "> ...");
        this.sum = sum;
    }

    public int getSum() {
        return sum;
    }
}
