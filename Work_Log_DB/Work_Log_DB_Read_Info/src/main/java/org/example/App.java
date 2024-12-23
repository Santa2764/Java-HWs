package org.example;

import org.example.entity.Notice;
import org.example.utils.WorkDb;

import java.sql.SQLException;
import java.util.List;

public class App {
    public static void main( String[] args ) {
        List<Notice> notices;
        while (true) {
            try {
                notices = WorkDb.getAllTypeNotices("INFO");  // получение notices тип INFO
                notices.forEach(n -> {
                    System.out.println(n);
                    try {
                        WorkDb.deleteById(n.getId());  // удаление записи
                        System.out.println("Deleted...\n");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
                System.out.println();
                Thread.sleep(5000);
            } catch (SQLException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
