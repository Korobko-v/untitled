package org.levelup.trello.jdbc;

import javax.naming.CompositeName;
import javax.naming.InvalidNameException;
import java.util.Enumeration;

public class jndiApp {
    public static void main(String[] args) throws InvalidNameException {
        CompositeName objectName = new CompositeName("C:\\Program Files\\Java");

        Enumeration<String> elements = objectName.getAll();
        while(elements.hasMoreElements()) {
            System.out.println(elements.nextElement());
        }
        objectName.add("\\Core");
        System.out.println(objectName.getAll());
    }
}
