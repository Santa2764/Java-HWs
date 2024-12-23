package com.company;

import com.company.utils.FileWork;
import com.company.utils.ListWork;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

public class Task1 {
    public static void main(String[] args) {
        Path pathFile1 = Path.of(".", "file1.txt");
        Path pathFile2 = Path.of(".", "file2.txt");

        File file1 = pathFile1.toFile();
        List<String> stringsFromFile1 = FileWork.getStringsFromFile(file1);

        File file2 = pathFile2.toFile();
        List<String> stringsFromFile2 = FileWork.getStringsFromFile(file2);

        if (stringsFromFile1 != null && stringsFromFile2 != null) {
            List<String> uniqueList1 = ListWork.getUniquesByAnotherList(stringsFromFile1, stringsFromFile2);
            List<String> uniqueList2 = ListWork.getUniquesByAnotherList(stringsFromFile2, stringsFromFile1);

            if (uniqueList1.isEmpty() && uniqueList2.isEmpty()) {
                System.out.println("The lines match");
            }
            else {
                System.out.println("Unique for file1:");
                uniqueList1.forEach(System.out::println);

                System.out.println("\nUnique for file2:");
                uniqueList2.forEach(System.out::println);
            }
        }
    }
}
