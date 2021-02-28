package org.levelup.reflection.annotationFilter.transport;

import lombok.ToString;
import org.levelup.reflection.annotationFilter.Flyable;

@ToString
public class Aircraft extends Machine implements Flyable {
    public String airline;
    public int capacity;

    public Aircraft() {
        hasWheels = false;
        name = "Самолёт";
    }

    @Override
    public void fly() {
        System.out.println("Полетели");
    }
}
