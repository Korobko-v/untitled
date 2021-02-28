package org.levelup.reflection.annotationFilter.animals;

public interface SwimmableAnimal {
    default void swim() {
        System.out.println("Буль");
    }
}
