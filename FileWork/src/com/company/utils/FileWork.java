package com.company.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileWork {
    public static void clearFile(Path path) throws IOException {
        Files.write(path, new byte[0], StandardOpenOption.TRUNCATE_EXISTING);
    }

    public static boolean checkFileExist(final File file) {
        if (!file.exists()) {
            System.out.println(file.getPath() + " does not exist");
            return false;
        }
        return true;
    }

    public static void appendStrToFile(Path path, String str) throws IOException {
        Files.writeString(path, str + "\n", StandardOpenOption.APPEND);
    }

    public static List<String> getStringsFromFile(File file) {
        if (!FileWork.checkFileExist(file)) {
            return null;
        }
        List<String> stringsFromFile;
        try {
            stringsFromFile = Files.readAllLines(file.toPath());
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return stringsFromFile;
    }

    public static String getLineByNumber(Path path, int number) throws IOException {
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            int currLine = 0;

            while ((line = br.readLine()) != null) {
                if (currLine == number) {
                    return line;
                }
            }
        }
        throw new IllegalArgumentException("Line number out of range...");
    }
}
