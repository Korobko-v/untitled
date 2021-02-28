package org.levelup.reflection;

import lombok.SneakyThrows;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Random;

public class RandomIntegerAnnotationProcessor {

    @SneakyThrows
    public Person generatePerson() {

        Class<Person> personClass = Person.class;

        //Создадим объект
        Constructor<Person> defaultConstructor = personClass.getDeclaredConstructor();
        Person object = defaultConstructor.newInstance();

        Field[] allFields = personClass.getDeclaredFields();
        for (Field field : allFields) {
            RandomInteger annotation = field.getAnnotation(RandomInteger.class);
            if (annotation != null) {
                Random r = new Random();
                int randomInteger = r.nextInt(annotation.max() - annotation.min()) + annotation.min();

                //Установка значения в поле объекта
                field.setAccessible(true);
                field.set(object, randomInteger);
            }
        }
        return object;
    }

}
