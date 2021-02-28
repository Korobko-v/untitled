package org.levelup.reflection.annotationFilter.animals;

import lombok.ToString;

@ToString
public class Dog extends Animal implements Walkable {
    public String breed;
    public boolean goodBoy;

    public Dog() {
        age = 5;
        type = "Dog";
    }

    @Override
    public void walk() {

    }

    @Override
    void voice() {
        System.out.println("Гав!");
    }
}
