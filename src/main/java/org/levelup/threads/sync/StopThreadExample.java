package org.levelup.threads.sync;

import lombok.SneakyThrows;

public class StopThreadExample {
    @SneakyThrows
    public static void main(String[] args) {
        Thread thread = new Thread(new Worker(), "worker_thread");
        thread.start();

        Thread.sleep(100);
        System.out.println("Send first notification to the thread");
        thread.interrupt();

        Thread.sleep(100);
            System.out.println("Send second notification");
            thread.interrupt();
    }

    public static class Worker implements Runnable {
        @Override
        public void run() {
//            Thread.currentThread().isInterrupted(); - возвращает флаг interrupted
//           Thread.interrupted(); - возвращает флаг interrupted, но после этого очищает флаг,
            //возвращая false
            int interruptionCount = 0;
            int value = 1;
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    if (interruptionCount < 1) {
                        System.out.println(Thread.currentThread().getName() + " ignored interruption");
                        interruptionCount++;
                        Thread.interrupted(); // сбросили флаг в false
                    }
                    else {
                        break;
                    }
                }

                System.out.println("Square number: " + (value * value));
                value++;
            }
            System.out.println("Thread finished its work");
        }
    }
}
