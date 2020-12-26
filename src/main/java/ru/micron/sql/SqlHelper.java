package ru.micron.sql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class SqlHelper {
    protected Connection CONNECTION;

    @Autowired
    public SqlHelper(Connection CONNECTION) {
        this.CONNECTION = CONNECTION;
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