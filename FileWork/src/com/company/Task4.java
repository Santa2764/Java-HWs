package com.company;

import com.company.utils.FileWork;
import com.company.utils.ListWork;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class Task4 {
    private static final int SIZE_ARR = 10;
    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 100;

    private static List<Integer> list;

    public static void main(String[] args) {
        Path pathFile = Path.of(".", "userNum.txt");
        File file = pathFile.toFile();

        if (!FileWork.checkFileExist(file)) {
            return;
        }

        // рандом
        Random r = new Random();
        List<Integer> list = Stream.generate(() -> r.nextInt(MIN_VALUE, MAX_VALUE)).limit(SIZE_ARR).toList();

        // в первой строке файла - исходный массив
        writeListToFile(file.toPath(), list);

        // во второй строке файла - массив чётных
        List<Integer> evenNums = ListWork.getFilteredList(list, n -> n % 2 == 0);
        writeListToFile(file.toPath(), evenNums);

        // в третьей строке файла - массив нечётных
        List<Integer> oddNums = ListWork.getFilteredList(list, n -> n % 2 != 0);
        writeListToFile(file.toPath(), oddNums);

        // в четвёртой строке файла - массив перевернутый
        List<Integer> reverseList = list.reversed();
        writeListToFile(file.toPath(), reverseList);
    }

    public static void writeListToFile(Path path, List<Integer> list) {
        try {
            String strList = ListWork.listToString(list, " ");  // list in str
            FileWork.appendStrToFile(path, strList);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
