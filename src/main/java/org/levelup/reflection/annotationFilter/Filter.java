package org.levelup.reflection.annotationFilter;

import org.levelup.reflection.ReflectionClass;
import org.reflections.Reflections;

import java.util.Set;

public class Filter {
    public void filterClasses (String line) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String pack = "org.levelup.reflection.annotationFilter." + line;
        Reflections reflections = new Reflections(pack);
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(ReflectionClass.class);
        for (Class c : classes) {
            Class<?> newClass = c.forName(c.getName());
            System.out.println(newClass.newInstance().toString());
        }
        }
    }

