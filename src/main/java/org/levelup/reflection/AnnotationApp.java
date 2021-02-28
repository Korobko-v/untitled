package org.levelup.reflection;


import java.lang.reflect.Field;

public class AnnotationApp {
    public static void main(String[] args) {
        RandomIntegerAnnotationProcessor processor = new RandomIntegerAnnotationProcessor();
        Person person = processor.generatePerson();
        System.out.println("Age: " + person.getAge());
        System.out.println("Weight: " + person.getWeight());
        System.out.println("Growth: " + person.getGrowth());
    }
}
