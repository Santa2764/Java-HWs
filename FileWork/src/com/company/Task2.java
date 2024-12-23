package com.company;

import com.company.utils.FileWork;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public class Task2 {
    private static List<String> stringsFromFile;

    public static void main(String[] args) {
        Path pathFile = Path.of(".", "file1.txt");
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

        if (stringsFromFile != null) {
            Optional<String> longestStr = stringsFromFile.stream()
                    .max((s1, s2) -> Integer.compare(s1.length(), s2.length()));
            longestStr.ifPresent(s -> {
                System.out.println("Longest string length: " + s.length());
                System.out.println("String: " + s);
            });
        }
    }
}
