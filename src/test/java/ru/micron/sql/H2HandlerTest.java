package ru.micron.sql;

import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.micron.CatalogItem;
import ru.micron.config.SpringAppContext;

public class H2HandlerTest extends TestCase {

    @Test
    public void testAdd() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringAppContext.class);
        H2Handler h2Handler = context.getBean("h2HandlerBean", H2Handler.class);
        h2Handler.add(new CatalogItem("phone", "iphone 5"));
        h2Handler.add(new CatalogItem("phone 12", "iphone 5"));
        h2Handler.add(new CatalogItem("phone x"));
        h2Handler.printTable();
        context.close();
    }

    @Test
    public void testDeleteById() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringAppContext.class);
        H2Handler h2Handler = context.getBean("h2HandlerBean", H2Handler.class);
        h2Handler.add(new CatalogItem("phone", "iphone 5"));
        h2Handler.add(new CatalogItem("phone 12", "iphone 5"));
        h2Handler.add(new CatalogItem("phone x"));
        h2Handler.printTable();
        h2Handler.deleteById(1);
        context.close();
    }

    @Test
    public void testDeleteByName() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringAppContext.class);
        H2Handler h2Handler = context.getBean("h2HandlerBean", H2Handler.class);
        h2Handler.add(new CatalogItem("phone", "iphone 5"));
        h2Handler.add(new CatalogItem("phone 12", "iphone 5"));
        h2Handler.add(new CatalogItem("phone x"));
        h2Handler.printTable();
        System.out.println("\n");
        h2Handler.deleteByName("phone 12");
        h2Handler.printTable();
        context.close();
    }

    @Test
    public void testEditDescription() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringAppContext.class);
        H2Handler h2Handler = context.getBean("h2HandlerBean", H2Handler.class);
        h2Handler.add(new CatalogItem("phone", "iphone 5"));
        h2Handler.add(new CatalogItem("phone 12", "iphone 5"));
        h2Handler.add(new CatalogItem("phone x"));
        h2Handler.printTable();
        System.out.println("\n");
        h2Handler.editDescription(2, "hahaha TEST");
        h2Handler.printTable();
        context.close();
    }

    @Test
    public void testGetByName() {

    }

    @Test
    public void testPrintTable() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringAppContext.class);
        H2Handler h2Handler = context.getBean("h2HandlerBean", H2Handler.class);
        h2Handler.add(new CatalogItem("phone", "iphone 5"));
        h2Handler.add(new CatalogItem("phone 12", "iphone 5"));
        h2Handler.add(new CatalogItem("phone x"));
        h2Handler.printTable();
        context.close();
    }
}