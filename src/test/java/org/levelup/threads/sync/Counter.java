package org.levelup.threads.sync;

public class Counter {

    private final Object mutex = new Object();
    private int value;

    public void increment() {
        synchronized (this) {
            value++;
        }
    }

    public int getValue() {
        synchronized (mutex) {
            return value;
        }
    }
}
