package com.company.exceptions;

public class InactivityGameConsoleException extends Exception {
    int waitingCounter;

    public InactivityGameConsoleException(String message, int waitingCounter) {
        super(message);
        this.waitingCounter = waitingCounter;
    }

    public int getWaitingCounter() {
        return waitingCounter;
    }
}
