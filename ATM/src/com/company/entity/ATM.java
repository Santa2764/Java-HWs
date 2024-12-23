package com.company.entity;

import com.company.exceptions.*;

import java.util.Arrays;
import java.util.Random;

/**
 A specific ATM class that provides access to basic ATM operations
 */
public class ATM {
    private final int minWithdrawalAmount;
    private final int maxWithdrawalAmount;
    private final int maxGetCountBanknotes;
    private final int maxDepositMoney;
    private final int minDepositMoney;

    private final int id;
    private BanknoteContainer[] banknotes;
    private int currDeposit;

    public ATM(
            int minWithdrawalAmount,
            int maxWithdrawalAmount,
            int maxGetCountBanknotes,
            int maxDepositMoney,
            int minDepositMoney) {
        this.id = new Random().nextInt(100, 100000);
        this.minWithdrawalAmount = minWithdrawalAmount;
        this.maxWithdrawalAmount = maxWithdrawalAmount;
        this.maxGetCountBanknotes = maxGetCountBanknotes;
        this.maxDepositMoney = maxDepositMoney;
        this.minDepositMoney = minDepositMoney;
    }


    public void initialize(int sum, int[] denominations) throws AtmException {
        int balanceSum = sum;

        // создаём контейнеры банкнот
        banknotes = new BanknoteContainer[denominations.length];
        for (int i = 0; i < banknotes.length; i++) {
            banknotes[i] = new BanknoteContainer(denominations[i]);
        }

        // распределяем равномерно номиналы
        int i = banknotes.length - 1;
        while (balanceSum > 0) {
            int denomination = banknotes[i].getDenomination();

            if (denomination <= balanceSum) {
                banknotes[i].addCurrentCount(1);
                balanceSum -= denomination;
            }

            i = (i == 0) ? banknotes.length - 1 : i - 1;

            // проверка на невозможность распределения
            boolean canDistributeFurther = false;
            for (BanknoteContainer banknote : banknotes) {
                if (banknote.getDenomination() <= balanceSum) {
                    canDistributeFurther = true;
                    break;
                }
            }

            if (!canDistributeFurther && balanceSum > 0) {
                throw new ImpossibleIssueAmountException(sum);  // невозможно равномерно распределить сумму с заданными номиналами
            }
        }
    }

    public void depositMoney(int denomination) throws AtmException {
        if (currDeposit + denomination > maxDepositMoney) {
            throw new MaxDepositException(maxDepositMoney);
        }

        if (hasDenomination(denomination)) {
            BanknoteContainer banknote = getBanknoteByDenomination(denomination);
            if (banknote == null) {
                throw new DenominationNotAcceptedException(denomination);
            }
            else {
                banknote.addCurrentCount(1);
                currDeposit += denomination;
            }
        }
        else {
            throw new DenominationNotAcceptedException(denomination);
        }
    }

    public int clickDepositMoney() throws AtmException {
        if (currDeposit < minDepositMoney) {
            throw new MinDepositException(minDepositMoney);
        }
        // возвращаем перед успешной операции сумму всех укзаных денег
        int tempCurrDeposit = currDeposit;
        currDeposit = 0;
        return tempCurrDeposit;
    }

    public BanknoteContainer[] withdrawalMoney(int sum) throws AtmException {
        if (minWithdrawalAmount > sum) {
            throw new MinWithdrawalSumException(minWithdrawalAmount);  // маленькая сумма
        }
        if (maxWithdrawalAmount < sum) {
            throw new MaxWithdrawalSumException(maxWithdrawalAmount);  // большая сумма
        }

        int balanceSum = sum;
        BanknoteContainer[] withdrawnBanknotes = new BanknoteContainer[banknotes.length];
        int withdrawnIndex = 0;

        // выдаём возможные номиналы и их кол-во
        for (int i = banknotes.length - 1; i >= 0 && balanceSum > 0; i--) {
            int denomination = banknotes[i].getDenomination();
            int count = balanceSum / denomination;
            int availableCount = banknotes[i].getCurrentCount();

            int countToWithdraw = Math.min(count, availableCount);
            if (countToWithdraw > 0) {
                withdrawnBanknotes[withdrawnIndex++] = new BanknoteContainer(denomination, count);
                balanceSum -= countToWithdraw * denomination;
            }
        }

        if (balanceSum > 0) {
            throw new ImpossibleIssueAmountException(sum);  // невозможно выдать указанную сумму с доступными номиналами
        }

        int countWithdrawnBanknotes = getAllCountBanknotes(withdrawnBanknotes);
        if (countWithdrawnBanknotes > maxGetCountBanknotes) {
            throw new MaxCountBanknotesException(maxGetCountBanknotes);  // количество купюр превышает макс лимит
        }

        for (int i = 0; i < withdrawnIndex; i++) {
            BanknoteContainer withdrawn = withdrawnBanknotes[i];
            for (BanknoteContainer banknote : banknotes) {
                if (withdrawn.getDenomination() == banknote.getDenomination()) {
                    banknote.removeCurrentCount(withdrawn.getCurrentCount());
                    break;
                }
            }
        }

        return Arrays.copyOf(withdrawnBanknotes, withdrawnIndex);  // убираем null
    }

    public void show() {
        System.out.println("-------------------");
        System.out.println("ID: " + id);
        System.out.println("Cash: " + getCashAmount());
        System.out.println("Min withdrawal amount: " + minWithdrawalAmount);
        System.out.println("Max withdrawal amount: " + maxWithdrawalAmount);
        System.out.println("Max get count banknotes: " + maxGetCountBanknotes);
        System.out.println("Min deposit: " + minDepositMoney);
        System.out.println("Max deposit: " + maxDepositMoney);

        if (banknotes == null) {
            System.out.println("No banknotes yet");
            return;
        }

        for (BanknoteContainer banknote : banknotes) {
            System.out.println(banknote.getDenomination() + " - " + banknote.getCurrentCount());
        }
    }

    public int getId() {
        return id;
    }

    public int getCashAmount() {
        int sum = 0;
        for (BanknoteContainer banknote : banknotes) {
            sum += banknote.getTotalSum();
        }
        return sum;
    }


    private int getAllCountBanknotes(BanknoteContainer[] banknotes) {
        int count = 0;
        for (BanknoteContainer banknote : banknotes) {
            if (banknote != null) {
                count += banknote.getCurrentCount();
            }
        }
        return count;
    }

    private BanknoteContainer getBanknoteByDenomination(int denomination) {
        for (BanknoteContainer banknote : banknotes) {
            if (banknote.getDenomination() == denomination) {
                return banknote;
            }
        }
        return null;
    }

    private boolean hasDenomination(int denomination) {
        for (BanknoteContainer banknote : banknotes) {
            if (banknote.getDenomination() == denomination) {
                return true;
            }
        }
        return false;
    }
}
