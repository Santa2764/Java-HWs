package com.company.tests;

import com.company.entity.ATM;

public class TaskFactory {
    public static ATM getATM() {
        int minWithdrawalAmount = TestUtils.getRandomInteger(1, 5) * 100;
        int maxWithdrawalAmount = TestUtils.getRandomInteger(5, 10) * 100;
        if (minWithdrawalAmount > maxWithdrawalAmount) {
            int temp = minWithdrawalAmount;
            minWithdrawalAmount = maxWithdrawalAmount;
            maxWithdrawalAmount = temp;
        }

        int maxGetCountBanknotes = TestUtils.getRandomInteger(10, 20) * 10;

        int maxDepositMoney = TestUtils.getRandomInteger(5, 10) * 1000;
        int minDepositMoney = TestUtils.getRandomInteger(1, 5) * 100;
        if (minDepositMoney > maxDepositMoney) {
            int temp = minDepositMoney;
            minDepositMoney = maxDepositMoney;
            maxDepositMoney = temp;
        }
        return new ATM(minWithdrawalAmount, maxWithdrawalAmount, maxGetCountBanknotes, maxDepositMoney, minDepositMoney);
    }

    public static ATM[] getATMs() {
        int count = TestUtils.getRandomInteger(1, 3);
        ATM[] atms = new ATM[count];

        for (int i = 0; i < count; i++) {
            atms[i] = getATM();
        }

        return atms;
    }
}
