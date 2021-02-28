package org.levelup.reflection.annotationFilter.animals;

import lombok.ToString;
import org.levelup.reflection.ReflectionClass;

@ReflectionClass
@ToString
public class Dolphin extends Animal implements SwimmableAnimal {
    public final boolean isSmart = true;
    public String color;

    public Dolphin() {
        color = "Blue";
        age = 2;
    }
    @Override
    void voice() {
        System.out.println("Кикикикикикикики");
    }


}
