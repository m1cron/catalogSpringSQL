package ru.micron.sql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.ResultSet;

@Component("h2HandlerBean")
public class H2Handler {
    private final SqlHelper sqlHelperBean;

    @Autowired
    public H2Handler(SqlHelper sqlHelperBean) {
        this.sqlHelperBean = sqlHelperBean;
    }

    @PostConstruct
    public void init() {
        sqlHelperBean.execute("CREATE TABLE catalog (GREETING VARCHAR(6), TARGET VARCHAR(6))");
        sqlHelperBean.execute("INSERT INTO catalog VALUES('Hello','World')");
    }

    @PreDestroy
    public void destroy() {
        sqlHelperBean.execute("DELETE FROM catalog");
    }

    public void export() {
        sqlHelperBean.execute("SELECT * FROM catalog", conn -> {
            try (ResultSet rs = conn.executeQuery()) {
                while (rs.next()) {
                    System.out.printf("%s, %s!%n",
                            rs.getString(1),
                            rs.getString("TARGET"));
                }
            }
            return null;
        });
    }
}
