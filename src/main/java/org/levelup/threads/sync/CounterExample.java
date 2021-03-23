package org.levelup.threads.sync;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

public class CounterExample {

    @SneakyThrows
    public static void main(String[] args) {
        Counter counter = new Counter();

        Thread t1 = new Thread(new Incrementer(counter, 100), "t1");
        Thread t2 = new Thread(new Incrementer(counter, 100), "t2");
        Thread t3 = new Thread(new Incrementer(counter, 100), "t3");

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println(counter.getValue());
    }

    @RequiredArgsConstructor
    public static class Incrementer implements Runnable {

        private final Counter counter;
        private final int timeToSleepInMillis;

        @SneakyThrows
        @Override
        public void run() {
            for (int i = 0; i < 30; i++) {
                counter.increment();
                Thread.sleep(timeToSleepInMillis);
            }
        }
    }
}
