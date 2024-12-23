package org.example.utils;

import org.example.entity.Notice;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkDb {
    private static final String QUERY_INSERT = """
            INSERT INTO notice(message, type, processed)
            VALUES (?, ?, ?)
            """;
    private static final String QUERY_SELECT_TYPE = """
            SELECT id, message, type, processed FROM notice
            WHERE type = ? AND processed = FALSE
            """;
    private static final String QUERY_DELETE_BY_ID = "DELETE FROM notice WHERE id = ?";
    private static final String QUERY_UPDATE = """
            UPDATE notice
            SET message = ?, type = ?, processed = ?
            WHERE id = ?
            """;

    // Получение соединения с БД
    private static Connection getConnection() throws SQLException {
        String url = AppProperty.getProperty("db.driver");
        String username = AppProperty.getProperty("db.user");
        String password = AppProperty.getProperty("db.password");
        return DriverManager.getConnection(url, username, password);
    }

    // Метод для вставки записи в таблицу notice
    public static void insert(String message, String type, boolean processed) throws SQLException {
        if (message == null || type == null) {
            throw new IllegalArgumentException("Message and type cannot be null");
        }
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(QUERY_INSERT)) {
            ps.setString(1, message);
            ps.setString(2, type);
            ps.setBoolean(3, processed);
            ps.executeUpdate();
        }
    }

    // Метод для выборки записей по типу
    public static List<Notice> getAllTypeNotices(String type) throws SQLException {
        List<Notice> notices = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(QUERY_SELECT_TYPE)) {
            ps.setString(1, type);
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String message = resultSet.getString("message");
                    boolean processed = resultSet.getBoolean("processed");
                    Notice notice = new Notice(id, message, type, processed);
                    notices.add(notice);
                }
            }
        }
        return notices;
    }

    // Метод для удаления записи по ID
    public static void deleteById(int id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(QUERY_DELETE_BY_ID)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    // Метод для обновления записи
    public static void update(int id, String message, String type, boolean processed) throws SQLException {
        if (message == null || type == null) {
            throw new IllegalArgumentException("Message and type cannot be null");
        }
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(QUERY_UPDATE)) {
            ps.setString(1, message);
            ps.setString(2, type);
            ps.setBoolean(3, processed);
            ps.setInt(4, id);
            ps.executeUpdate();
        }
    }
}
