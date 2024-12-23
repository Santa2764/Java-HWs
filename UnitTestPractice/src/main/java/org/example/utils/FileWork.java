package org.example.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileWork {
    public static void clearFile(final Path path) throws IOException {
        Files.write(path, new byte[0], StandardOpenOption.TRUNCATE_EXISTING);
    }

    public static boolean checkFileExist(final File file) {
        if (!file.exists()) {
            System.out.println(file.getPath() + " does not exist");
            return false;
        }
        return true;
    }

    public static void appendStrToFile(final Path path, final String str) throws IOException {
        Files.writeString(path, str + "\n", StandardOpenOption.APPEND);
    }

    public static List<String> getStringsFromFile(final File file){
        if (!checkFileExist(file)) {
            throw new IllegalArgumentException(file.getPath() + " does not exist");
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
}
