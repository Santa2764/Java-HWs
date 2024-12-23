package com.company.utils;

import java.util.Scanner;

public class Input {
    public static int inputInteger(String text, String errText, int min, int max) {
        Scanner scanner = new Scanner(System.in);
        int number;

        System.out.print(text);
        do {
            while (!scanner.hasNextInt()) {
                System.out.println(errText);
                scanner.next();
            }
            number = scanner.nextInt();
        } while (number < min || number > max);

        scanner.nextLine();
        return number;
    }
}
