package com.company.exceptions;

public class LimitDenominationExceededException extends AtmException {
    private final int denomination;
    private final int maxLimit;

    public LimitDenominationExceededException(int denomination, int maxLimit) {
        super("The limit <" + maxLimit + "> on the number of banknotes of a given denomination <" + denomination + "> has been exceeded...");
        this.denomination = denomination;
        this.maxLimit = maxLimit;
    }

    public int getDenomination() {
        return denomination;
    }

    public int getMaxLimit() {
        return maxLimit;
    }
}
