package ru.micron.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import ru.micron.sql.H2Handler;
import ru.micron.sql.SqlHelper;

import javax.xml.transform.Source;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
@PropertySource("classpath:application.properties")
public class SpringAppContext {
    @Value("${db.url}")
    private String db_url;

    @Bean
    public Connection dataSourceBean() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(db_url);
        } catch (SQLException throwables) {
            System.err.print("Connection error!\n");
            throwables.printStackTrace();
        }
        return connection;
    }

    @Bean
    public SqlHelper sqlHelperBean() {
        return new SqlHelper(dataSourceBean());
    }

    @Bean
    public H2Handler h2HandlerBean() {
        return new H2Handler(sqlHelperBean());
    }
}