package com.company;

import com.company.utils.FileWork;
import com.company.utils.ListWork;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Task3 {
    private static List<String> stringsFromFile;
    private static List<List<Integer>> listsInteger;

    public static void main(String[] args) {
        Path pathFile = Path.of(".", "numFile.txt");
        File file = pathFile.toFile();

        if (!FileWork.checkFileExist(file)) {
            return;
        }
        try {
            stringsFromFile = Files.readAllLines(file.toPath());
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }

        if (stringsFromFile == null) {
            return;
        }

        // получаем список списков для Integer
        listsInteger = new ArrayList<>();
        for (String s : stringsFromFile) {
            listsInteger.add(ListWork.getListIntByStrNum(s));
        }

        // вывод
        listsInteger.forEach(list -> {
            list.forEach(el -> System.out.print(el + " | "));
            System.out.println();
        });

        // макс и мин в каждом списке
        System.out.println("\nMax elements:");
        listsInteger.forEach(list -> {
            int val = ListWork.getFindMaxElem(list, Integer::compareTo);
            System.out.print(val + " | ");
        });

        System.out.println("\nMin elements:");
        listsInteger.forEach(list -> {
            int val = ListWork.getFindMaxElem(list, (el1, el2) -> Integer.compare(el2, el1));
            System.out.print(val + " | ");
        });

        // макс и мин
        int max = ListWork.getFindMaxElemNestedArr(listsInteger, Integer::compareTo);
        System.out.println("\n\nMax element: " +  max);
        int min = ListWork.getFindMaxElemNestedArr(listsInteger, (el1, el2) -> Integer.compare(el2, el1));
        System.out.println("Min elements: " + min);

        // сумма
        int sum = ListWork.getSumNestedList(listsInteger);
        System.out.println("Total sum: " +  sum);
    }
}
