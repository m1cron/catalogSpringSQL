package ru.micron.sql;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component("sqlHelperBean")
public class SqlHelper {
    protected Connection CONNECTION;

    public SqlHelper(String db_url) {
        try {
            CONNECTION = DriverManager.getConnection(db_url);
            System.out.print("Connection Success!\n");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void execute(String sql) {
        execute(sql, PreparedStatement::execute);
    }

    public <T> T execute(String sql, SqlExecutor<T> executor) {
        try (PreparedStatement ps = CONNECTION.prepareStatement(sql)) {
            return executor.execute(ps);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}