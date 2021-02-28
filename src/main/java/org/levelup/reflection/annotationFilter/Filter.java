package org.levelup.reflection.annotationFilter;

import lombok.SneakyThrows;
import org.levelup.reflection.ReflectionClass;
import org.reflections.Reflections;

import java.util.Set;


public class Filter {
    @SneakyThrows
    public void filterClasses (String line) {
        String pack = "org.levelup.reflection." + line;
        Reflections reflections = new Reflections(pack);
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(ReflectionClass.class);
        for (Class c : classes) {
            Class<?> newClass = Class.forName(c.getName());
            System.out.println(newClass.getDeclaredConstructor().newInstance().toString());
        }
    }
}

