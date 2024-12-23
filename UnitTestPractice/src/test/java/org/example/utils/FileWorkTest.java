package org.example.utils;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileWorkTest {
    private static final String TEXT_TO_FILE = "Hello, World!\nHello, Java!";
    private static final Path NON_EXISTING_FILE_PATH = Path.of(".", "orders", "123.csv");
    private Path tempFile;

    @BeforeEach
    public void setup() throws IOException {
        tempFile = Files.createTempFile("testFile", ".txt");
        Files.writeString(tempFile, TEXT_TO_FILE);
    }

    @AfterEach
    public void cleanup() throws IOException {
        Files.deleteIfExists(tempFile);
    }

    @Test
    public void clearFile_ShouldClearFile_IfFileExist() {
        try {
            FileWork.clearFile(tempFile);
            String content = Files.readString(tempFile);
            boolean condition = content.isEmpty();
            assertTrue(condition, "File should be empty after calling clearFile");
        }
        catch (IOException e) {
            fail("IOException was thrown: " + e.getMessage());
        }
    }

    @Test
    public void clearFile_ShouldThrowIOException_WhenFileNotExist() {
        assertThrows(IOException.class, () -> FileWork.clearFile(NON_EXISTING_FILE_PATH),
                "Expected IOException to be thrown when trying to clear a non-existing file");
    }

    @Test
    public void checkFileExist_ShouldReturnTrue_WhenFileExists() {
        boolean condition = FileWork.checkFileExist(tempFile.toFile());
        assertTrue(condition, "File '" + tempFile + "' should exist");
    }

    @Test
    public void checkFileExist_ShouldReturnFalse_WhenFileNotExist() {
        boolean condition = FileWork.checkFileExist(NON_EXISTING_FILE_PATH.toFile());
        assertFalse(condition, "File '" + NON_EXISTING_FILE_PATH + "' should not exist");
    }

    @Test
    public void appendStrToFile_ShouldAppendStringToFile() {
        try {
            FileWork.appendStrToFile(tempFile, TEXT_TO_FILE);
            String content = Files.readString(tempFile);
            assertTrue(content.contains(TEXT_TO_FILE), "File should contain appended text");
        }
        catch (IOException e) {
            fail("IOException was thrown: " + e.getMessage());
        }
    }

    @Test
    public void getStringsFromFile_ShouldGetStringsFromFile_HelloWorld() {
        File file = tempFile.toFile();
        List<String> lines = FileWork.getStringsFromFile(file);
        assertNotNull(lines, "List of strings should not be null");
        assertEquals(2, lines.size(), "There should be two lines in the file");
        assertEquals("Hello, World!", lines.get(0), "First line should be 'Hello, World!'");
        assertEquals("Hello, Java!", lines.get(1), "Second line should be 'Hello, Java!'");
    }

    @Test
    public void getStringsFromFile_ShouldThrowIllegalArgumentException_WhenFileNotExist() {
        assertThrows(IllegalArgumentException.class, () -> FileWork.getStringsFromFile(NON_EXISTING_FILE_PATH.toFile()),
                "Expected IllegalArgumentException to be thrown when trying to clear a non-existing file");
    }
}
