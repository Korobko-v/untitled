package org.levelup.reflection.annotationFilter.transport;

import lombok.ToString;
import org.levelup.reflection.ReflectionClass;

@ToString
@ReflectionClass
public class Ship extends Machine implements Swimmable{
    public String type;
    public int countOfDecks;

    public Ship() {
        hasWheels = false;
        name = "Судно";
        type = "Фрегат";
        countOfDecks = 2;
    }


    @Override
    public void step() {
        System.out.println("Вот теперь норм");
    }
}
