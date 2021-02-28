package org.levelup.reflection.annotationFilter.transport;

public interface Movable {
    default void move() {
        System.out.println("Поiхали!");
    }
}
