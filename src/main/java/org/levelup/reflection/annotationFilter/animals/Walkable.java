package org.levelup.reflection.annotationFilter.animals;

public interface Walkable {
    default void walk() {
        System.out.println("Пошли");
    }
}
