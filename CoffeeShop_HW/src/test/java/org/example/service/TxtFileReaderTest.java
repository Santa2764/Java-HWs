package org.example.service;

import org.example.exception.FileException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.setProperty;

public class TxtFileReaderTest {
    @BeforeAll
    static void createProperty() {
        setProperty("test", "true");
    }

    @Test
    void readFile_ShouldReturnListOfStringsFromFile_WhenCalled() {
        TxtFileReader txtFileReader = new TxtFileReader("data.personal_position");
        try {
            List<String> actual =  txtFileReader.readFile();
            List<String> expected = new ArrayList<>();
            expected.add("Бариста");
            expected.add("Официант");
            expected.add("Кондитер");

            Assertions.assertEquals(expected, actual,"Read text");
        } catch (FileException e) {
            throw new RuntimeException(e);
        }
    }
}
