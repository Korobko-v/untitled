package org.levelup.reflection.annotationFilter.transport;

import lombok.ToString;
import org.levelup.reflection.ReflectionClass;

@ReflectionClass
@ToString
public class Car extends Machine implements Movable {
    public String color;
    public int maxSpeed;
    public int yearOfManufacture;

    public Car() {
        hasWheels = true;
        name = "Автомобиль";
        color = "White";
        maxSpeed = 180;
        yearOfManufacture = 2020;
    }

    @Override
    public void move() {
        System.out.println("Погнали");
    }
}
