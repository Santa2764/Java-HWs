package com.company.exceptions;

public class AtmIsNotFoundException extends AtmException {
    public AtmIsNotFoundException() {
        super("ATM is not found...");
    }
}
