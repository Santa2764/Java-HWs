package com.company.exceptions;

public class RanOutDenominationException extends AtmException {
    private final int denomination;

    public RanOutDenominationException(int denomination) {
        super("This denomination <" + denomination + "> ran out at the ATM...");
        this.denomination = denomination;
    }

    public int getDenomination() {
        return denomination;
    }
}
