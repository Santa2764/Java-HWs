package com.example.CoffeeShop_Spring.utils;

import java.time.LocalDate;
import java.util.Scanner;

public class Input {
    public static int inputInteger(String text, String errText, int min, int max) {
        Scanner scanner = new Scanner(System.in);
        int number;

        System.out.print(text);
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("Ошибка, введите целое число...");
                scanner.next();
            }
            number = scanner.nextInt();
            if (number < min || number > max) {
                System.out.println(errText);
            }
        } while (number < min || number > max);

        scanner.nextLine();
        return number;
    }

    public static double inputDouble(String text, String errText, double min, double max) {
        Scanner scanner = new Scanner(System.in);
        double number;

        System.out.print(text);
        do {
            while (!scanner.hasNextDouble()) {
                System.out.println("Ошибка, введите дробное число...");
                scanner.next();
            }
            number = scanner.nextDouble();
            if (number < min || number > max) {
                System.out.println(errText);
            }
        } while (number < min || number > max);

        scanner.nextLine();
        return number;
    }

    public static String inputString(String text, String errText) {
        Scanner scanner = new Scanner(System.in);
        String str;

        System.out.print(text);
        do {
            str = scanner.nextLine();
            if (str.isEmpty()) {
                System.out.println(errText);
            }
        } while (str.isEmpty());

        return str;
    }

    public static LocalDate inputDate(String text) {
        System.out.println(text);
        int day = Input.inputInteger(
                "Введите число: ",
                "Неверный ввод, нужно от 1 до 31",
                1, 31
        );
        int month = Input.inputInteger(
                "Введите месяц: ",
                "Неверный ввод, нужно от 1 до 12",
                1, 12
        );
        int year = Input.inputInteger(
                "Введите год: ",
                "Неверный ввод, нужно от 1900 до " + (TestUtils.CURRENT_YEAR),
                1, (TestUtils.CURRENT_YEAR)
        );
        return LocalDate.of(year, month, day);
    }
}
