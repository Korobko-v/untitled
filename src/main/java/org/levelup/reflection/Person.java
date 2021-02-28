package org.levelup.reflection;

import lombok.Getter;

@Getter
public class Person {

    @RandomInteger(min = 18, max = 70)
    private int age; // значение будет генерироваться автоматически

    @RandomInteger(min = 55, max = 110)
    private int weight;
    @RandomInteger(min = 150,max = 210)
    private int growth;
}
