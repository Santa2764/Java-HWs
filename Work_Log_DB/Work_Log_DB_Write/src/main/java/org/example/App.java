package org.example;

import org.example.utils.WorkDb;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Random;

public class App {
    private static final Random rand = new Random();
    private static final String[] MESSAGES = {
            "Новое сообщение от %s",
            "Произошла ошибка в %s"
    };
    private static final String[] TYPES = {
            "INFO",
            "WARN"
    };

    public static void main(String[] args) {
        while (true) {
            // рандомные значения
            String message = MESSAGES[rand.nextInt(MESSAGES.length)];
            message = String.format(message, LocalDateTime.now());

            String type = TYPES[rand.nextInt(TYPES.length)];
            type = String.format(type, LocalDateTime.now());

            try {
                WorkDb.insert(message, type, false);  // добавляем запись в БД
                System.out.println("Log send...");
                Thread.sleep(2000);
            } catch (SQLException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
