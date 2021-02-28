package org.levelup.reflection.annotationFilter.transport;

public interface Swimmable {
    default void swim() {
        System.out.println("Плавает говно, а судно ходит!");
        step();
    }
    void step();
}
