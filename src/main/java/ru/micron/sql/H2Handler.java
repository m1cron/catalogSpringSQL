package ru.micron.sql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.micron.CatalogItem;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.*;

@Component
public class H2Handler {
    private final SqlHelper sqlHelperBean;

    @Autowired
    public H2Handler(SqlHelper sqlHelperBean) {
        this.sqlHelperBean = sqlHelperBean;
    }

    @PostConstruct
    public void init() {
        sqlHelperBean.execute("CREATE TABLE catalog (" +
                                    "id          IDENTITY NOT NULL PRIMARY KEY," +
                                    "name        varchar  NOT NULL," +
                                    "description text)"
        );
    }

    @PreDestroy
    public void destroy() {
        sqlHelperBean.execute("DELETE FROM catalog");
    }

    public void add(CatalogItem obj) {
        sqlHelperBean.execute("INSERT INTO catalog (name, description) VALUES (?, ?)", ps -> {
            ps.setString(1, obj.getName());
            ps.setString(2, obj.getDescription() != null ? obj.getDescription() : "No description.");
            ps.execute();
            return null;
        });
    }

    public void deleteById(Integer id) {
        sqlHelperBean.execute("DELETE FROM catalog WHERE id = ?", ps -> {
            ps.setInt(1, id);
            ps.execute();
            return null;
        });
    }

    public void deleteByName(String name) {
        sqlHelperBean.execute("DELETE FROM catalog WHERE name = ?", ps -> {
            ps.setString(1, name);
            ps.execute();
            return null;
        });
    }

    public void editDescription(Integer id, String newDescription) {
        sqlHelperBean.execute("UPDATE catalog SET description = ? WHERE id = ?", ps -> {
            ps.setString(1, newDescription);
            ps.setInt(2, id);
            ps.executeUpdate();
            return null;
        });
    }

    public CatalogItem getByName(String name) {
        return sqlHelperBean.execute("SELECT * FROM catalog WHERE id = ?", ps -> {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new CatalogItem(rs.getString(2), rs.getString(3));
            }
            return null;
        });
    }

    public void printTable() {
        sqlHelperBean.execute("SELECT * FROM catalog", ps -> {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    System.out.printf("%d\t%s\t%s\n",
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3));
                }
            }
            return null;
        });
    }
}