package com.company.exceptions;

public class DenominationNotAcceptedException extends AtmException {
    private final int denomination;

    public DenominationNotAcceptedException(int denomination) {
        super("This denomination <" + denomination + "> cannot be accepted by an ATM...");
        this.denomination = denomination;
    }

    public int getDenomination() {
        return denomination;
    }
}
