package org.levelup.reflection.annotationFilter;

public class FilterApp {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Filter filter = new Filter();
        filter.filterClasses("reflection");
    }
}
