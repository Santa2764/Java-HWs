package com.company.menu;

import com.company.entity.ATM;
import com.company.entity.Bank;
import com.company.entity.BanknoteContainer;
import com.company.exceptions.*;
import com.company.tests.TestUtils;
import com.company.util.Input;

public class AtmMenu {
    private final Bank bank;

    public AtmMenu(Bank bank) {
        this.bank = bank;
    }


    public void show() {
        System.out.println("ATM menu:");
        ShowMenu.show();
    }

    public int getChoice() {
        int maxChoice = ShowMenu.getSizeMenu();
        return Input.inputInteger(
                "\nPlease enter the number menu: ",
                "Invalid choice, need from " + ShowMenu.MIN_MENU_NUM + " to " + maxChoice,
                ShowMenu.MIN_MENU_NUM,
                maxChoice
        );
    }

    public boolean execute(int choiceMenu) throws AtmException {
        switch (choiceMenu) {
            case 1:
                getTotalCash();
                break;

            case 2:
                initializeAtms();
                break;

            case 3:
                loadCash();
                break;

            case 4:
                withdrawalCash();
                break;

            case 5:
                getAmountAtms();
                break;

            case 6:
                getAtmInfo();
                break;

            case 7:
                getAllInfoAtms();
                break;

            default:
                return false;
        }
        return true;
    }


    private void getTotalCash() {
        int total = bank.getTotalCash();
        System.out.println("Total cash: " + total);
    }

    public void initializeAtms() throws AtmException {
        ATM[] atms = bank.getAtms();
        for (ATM atm : atms) {
            int idAtm = atm.getId();
            int sum = TestUtils.getRandomInteger(1, 10) * 1000;
            int[] denominations = TestUtils.getRandomDenominations();
            try {
                bank.initializeAtm(idAtm, sum, denominations);
            }
            catch (ImpossibleIssueAmountException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    private void loadCash() {
        int idAtm = Input.inputInteger(
                "Input id atm: ",
                "Error input id atm...",
                1,
                100000
        );
        try {
            bank.checkConnection(idAtm);
            loadCashProcess(idAtm);
        }
        catch (AtmIsNotFoundException e) {
            System.out.println(e.getMessage());  // банкомат не найден
        }
        catch (AtmException _) { }
    }

    public void loadCashProcess(int idAtm) {
        int denomination;
        while (true) {
            denomination = Input.inputInteger(
                    "Input denomination (<0 finish this): ",
                    "Error input denomination...",
                    0,
                    1000
            );
            if (denomination <= 0) {
                break;
            }

            try {
                bank.loadCash(idAtm, denomination);
            }
            catch (AtmIsNotFoundException e) {
                System.out.println(e.getMessage());  // банкомат не найден
            }
            catch (DenominationNotAcceptedException e) {  // номинал не поддерживается
                System.out.println(e.getMessage());
            }
            catch (RanOutDenominationException e) {  // превышен макс. лимит кол-ва номинала
                System.out.println(e.getMessage());
            }
            catch (MaxDepositException e) {
                System.out.println(e.getMessage());  // превышен макс. депозит
            }
            catch (AtmException _) { }
        }

        try {
            int userCurrDeposit = bank.clickDepositMoney(idAtm);
            if (userCurrDeposit != -1) {
                System.out.println("!!! Your deposit successfully: " + userCurrDeposit + " !!!");
            }
        }
        catch (MinDepositException e) {
            System.out.println(e.getMessage());  // мин. депозит
            loadCashProcess(idAtm);  // запуск ввода снова
        }
        catch (AtmException _) { }
    }


    private void withdrawalCash() {
        int idAtm = Input.inputInteger(
                "Input id atm: ",
                "Error input id atm...",
                1,
                100000
        );
        try {
            bank.checkConnection(idAtm);
            loadWithdrawalProcess(idAtm);
        }
        catch (AtmIsNotFoundException e) {
            System.out.println(e.getMessage());  // банкомат не найден
        }
        catch (AtmException _) { }
    }

    public void loadWithdrawalProcess(int idAtm) {
        int sum = Input.inputInteger(
                "Input sum: ",
                "Error input sum...",
                1,
                100000
        );
        try {
            BanknoteContainer[] banknotes = bank.withdrawalCash(idAtm, sum);
            if (banknotes != null) {
                System.out.println("Your banknotes:");
                for (BanknoteContainer banknote : banknotes) {
                    System.out.println(banknote.getDenomination() + " (" + banknote.getCurrentCount() + ")");
                }
            }
        }
        catch (MinWithdrawalSumException e) {
            System.out.println(e.getMessage());
            loadWithdrawalProcess(idAtm);
        }
        catch (MaxWithdrawalSumException e) {
            System.out.println(e.getMessage());
            loadWithdrawalProcess(idAtm);
        }
        catch (ImpossibleIssueAmountException e) {
            System.out.println(e.getMessage());
            loadWithdrawalProcess(idAtm);
        }
        catch (LimitDenominationExceededException e) {
            System.out.println(e.getMessage());
            loadWithdrawalProcess(idAtm);
        }
        catch (AtmException _) { }
    }


    private void getAmountAtms() {
        System.out.println("Amount atms: " + bank.CountAtm);
    }

    private void getAtmInfo() {
        int idAtm = Input.inputInteger(
                "Input id atm: ",
                "Error input id atm...",
                1,
                100000
        );
        try {
            bank.showAtmInfo(idAtm);
        }
        catch (AtmIsNotFoundException e) {
            System.out.println(e.getMessage());
        }
        catch (AtmException _) { }
    }

    private void getAllInfoAtms() {
        bank.show();
    }
}
