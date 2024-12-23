package com.company.entity;

import com.company.exceptions.AtmException;
import com.company.exceptions.AtmIsNotFoundException;

import java.util.Arrays;

/**
 A class of bank that stores a network of ATMs and has transactions with specific ATMs upon request
 */
public class Bank {
    public final int CountAtm;
    private final ATM[] atms;

    public Bank(ATM[] atms) {
        this.atms = atms;
        CountAtm = atms.length;
    }


    public void initializeAtm(int idAtm, int sum, int[] denominations) throws AtmException {
        ATM atm = getAtmById(idAtm);
        if (atm == null) {
            return;
        }
        atm.initialize(sum, denominations);
    }

    public void checkConnection(int idAtm) throws AtmException {
        ATM atm = getAtmById(idAtm);
        if (atm == null) {
            throw new AtmIsNotFoundException();
        }
    }

    public void loadCash(int idAtm, int denomination) throws AtmException {
        ATM atm = getAtmById(idAtm);
        if (atm == null) {
            throw new AtmIsNotFoundException();
        }
        atm.depositMoney(denomination);
    }

    public int clickDepositMoney(int idAtm) throws AtmException {
        ATM atm = getAtmById(idAtm);
        if (atm != null) {
            return atm.clickDepositMoney();
        }
        return -1;
    }

    public BanknoteContainer[] withdrawalCash(int idAtm, int sum) throws AtmException {
        ATM atm = getAtmById(idAtm);
        if (atm != null) {
            return atm.withdrawalMoney(sum);
        }
        return null;
    }


    public void show() {
        for (ATM atm : atms) {
            atm.show();
        }
    }

    public int getTotalCash() {
        int sum = 0;
        for (ATM atm : atms) {
            sum += atm.getCashAmount();
        }
        return sum;
    }

    public ATM getAtmById(int id) {
        for (ATM atm : atms) {
            if (atm.getId() == id) {
                return atm;
            }
        }
        return null;
    }

    public void showAtmInfo(int id) throws AtmException {
        for (ATM atm : atms) {
            if (atm.getId() == id) {
                atm.show();
                return;
            }
        }
        throw new AtmIsNotFoundException();
    }

    public ATM[] getAtms() {
        return Arrays.copyOf(atms, atms.length);
    }
}
