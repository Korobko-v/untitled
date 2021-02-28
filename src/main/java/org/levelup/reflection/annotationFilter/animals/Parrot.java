package org.levelup.reflection.annotationFilter.animals;

import lombok.ToString;
import org.levelup.reflection.ReflectionClass;
import org.levelup.reflection.annotationFilter.Flyable;

@ReflectionClass
@ToString
public class Parrot extends Animal implements Flyable, Walkable {
    public boolean isSpeaking;
    public String color;

    public Parrot() {
        age = 2;
        type = "Parrot";
        isSpeaking = true;
        color = "green";
    }

    @Override
    public void fly() {

    }

    @Override
    void voice() {

    }
}
