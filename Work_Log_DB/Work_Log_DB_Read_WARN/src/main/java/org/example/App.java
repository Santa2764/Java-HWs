package org.example;

import org.example.entity.Notice;
import org.example.utils.WorkDb;

import java.sql.SQLException;
import java.util.List;

public class App {
    public static void main(String[] args) {
        List<Notice> notices;
        while (true) {
            try {
                notices = WorkDb.getAllTypeNotices("WARN");  // получение notices тип WARN
                notices.forEach(n -> {
                    try {
                        // обновляем запись
                        WorkDb.update(
                                n.getId(),
                                n.getMessage(),
                                n.getType(),
                                true
                        );
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
                System.out.println("Updated...\n");
                Thread.sleep(5000);
            } catch (SQLException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
