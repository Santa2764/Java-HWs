package com.company.entity;

import com.company.exceptions.AtmException;
import com.company.exceptions.LimitDenominationExceededException;
import com.company.exceptions.RanOutDenominationException;

/**
 Class container for storing a certain denomination in an ATM
 */
public class BanknoteContainer {
    public static final int MAX_COUNT = 500;
    private int currentCount;
    private int denomination;

    public BanknoteContainer(int denomination) {
        this.denomination = denomination;
    }

    public BanknoteContainer(int denomination, int currentCount) throws AtmException {
        this.denomination = denomination;
        setCurrentCount(currentCount);
    }

    public int getTotalSum() {
        return currentCount * denomination;
    }

    public void addCurrentCount(int count) throws AtmException {
        setCurrentCount(currentCount + count);
    }

    public void removeCurrentCount(int count) throws AtmException {
        setCurrentCount(currentCount - count);
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(int currentCount) throws AtmException {
        if (currentCount > MAX_COUNT) {
            throw new LimitDenominationExceededException(denomination, MAX_COUNT);
        }
        if (currentCount < 0) {
            throw new RanOutDenominationException(denomination);
        }
        this.currentCount = currentCount;
    }

    public int getDenomination() {
        return denomination;
    }

    public void setDenomination(int denomination) {
        this.denomination = denomination;
    }
}
