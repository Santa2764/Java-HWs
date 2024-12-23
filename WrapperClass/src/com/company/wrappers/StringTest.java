package com.company.wrappers;

import java.util.Scanner;

public class StringTest {
    public static void main(String[] args) {
        // ----------- 2 -----------
        String s1 = "Daniil";
        String s2 = new String("Soboliev");
        String s3 = new String(new char[] {'k', 's'});
        String s4 = new String(new byte[] {100, 97});

        StringBuilder sb = new StringBuilder("Daniil");
        String s5 = sb.toString();

        // ----------- 3 -----------
        String s6 = "Апельсин,Яблоко,Гранат,Груша";
        String[] strArr = s6.split(",");

        // самое длинное слово
        String maxLenVerb = strArr[0];
        for (int i = 1; i < strArr.length; i++) {
            if (strArr[i].length() > maxLenVerb.length()) {
                maxLenVerb = strArr[i];
            }
        }
        maxLenVerb = maxLenVerb.toLowerCase();
        System.out.println(maxLenVerb);

        // первые три символа
        String s7 = maxLenVerb.substring(0, 3);
        System.out.println(s7);

        //
        String s8 = "   Я_новая_строка      ";
        System.out.println(s8);
        s8 = s8.trim();
        System.out.println(s8);
        s8 = s8.replace('_', ' ');
        System.out.println(s8);

        //
        Scanner input = new Scanner(System.in);

        System.out.print("\nВведите текст: ");
        String s9 = input.nextLine();
        System.out.println("Вы ввели: " + s9);

        if (s9.toLowerCase().startsWith("запуск")) {
            System.out.println("Запускаем процесс");
        }
        if (s9.toLowerCase().endsWith("завершен")) {
            System.out.println("Процесс завершен");
        }
        if (s9.toLowerCase().contains("ошибка")) {
            System.out.println("Произошла ошибка");
        }
        System.out.println();

        // ----------- 4 -----------
        StringBuilder sb2 = new StringBuilder();
        sb2.append(s1).append(s2).append(s3)
            .append(s4).append(s5).append(s6)
            .append(s7).append(s8);

        int lenChapter1 = s1.length() + s2.length() + s3.length();
        sb2.insert(lenChapter1, "\n");

        int lenChapter2 = lenChapter1 + 1 + s4.length() + s5.length() + s6.length();
        sb2.insert(lenChapter2, "\n");

        String newString = sb2.toString();
        System.out.println(newString);
    }
}
