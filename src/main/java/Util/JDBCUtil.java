package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
    public static Connection getConnection(String url, String username, String password) {
        Connection c = null;
        try {
            c = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    public static Connection getConnection() {
        Connection c = null;
        try {
            // Load secrets from environment variables
            String url = System.getenv("DB_URL");
            String username = System.getenv("DB_USERNAME");
            String password = System.getenv("DB_PASSWORD");

            if (url == null || username == null || password == null) {
                throw new IllegalStateException("Database credentials are not set in environment variables.");
            }

            c = DriverManager.getConnection(url, username, password);
        } catch (SQLException | IllegalStateException e) {
            e.printStackTrace();
        }
        return c;
    }

    public static void closeConnection(Connection c) {
        try {
            if (c != null) {
                c.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
