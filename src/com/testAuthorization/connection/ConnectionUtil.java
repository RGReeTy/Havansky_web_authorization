package com.testAuthorization.connection;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionUtil {

    public static Connection getConnection() throws ClassNotFoundException, SQLException {

        return MySQLConnUtil.getMySQLConnection();
    }

    public static void closeQuietly(Connection conn) {
        try {
            conn.close();
        } catch (Exception e) {
        }
    }

    public static void rollbackQuietly(Connection conn) {
        try {
            conn.rollback();
        } catch (Exception e) {
        }
    }
}
