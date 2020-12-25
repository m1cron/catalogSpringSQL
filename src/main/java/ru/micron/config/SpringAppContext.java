package ru.micron.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import ru.micron.sql.H2Handler;
import ru.micron.sql.SqlHelper;

@Configuration
@PropertySource("classpath:application.properties")
public class SpringAppContext {
    @Value("${db.url}")
    private String db_url;

    @Bean
    @Scope("singleton")
    public SqlHelper sqlHelperBean() {
        return new SqlHelper(db_url);
    }

    @Bean
    @Scope("singleton")
    public H2Handler h2HandlerBean() {
        return new H2Handler(sqlHelperBean());
    }
}