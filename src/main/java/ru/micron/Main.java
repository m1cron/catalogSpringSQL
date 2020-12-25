package ru.micron;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.micron.config.SpringAppContext;
import ru.micron.sql.H2Handler;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringAppContext.class);

        H2Handler h2Handler = context.getBean("h2HandlerBean", H2Handler.class);
        h2Handler.add(new CatalogItem("phone", "iphone 5"));
        h2Handler.add(new CatalogItem("phone12", "iphone 5"));
        h2Handler.editDescription(2, "2348723tqdbzgj;dfzg");

        System.out.println(h2Handler.getByName("phone"));
        //h2Handler.printTable();

        context.close();
    }
}