package org.levelup.threads;

public class ThreadsExample {

    public static void main(String[] args) throws InterruptedException {
        PrintThread thread = new PrintThread();
        thread.start();

        Thread countDownThread = new Thread(new CountDown(), "count_down_thread");
        countDownThread.start();
//        countDownThread.join();

        Thread daemonThread = new Thread(() -> {
            while (true) {
                printString(String.valueOf(System.currentTimeMillis()));
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    printString("Interrupted...");
                }
            }
        }, "daemon_thread");

        daemonThread.setDaemon(true);
        daemonThread.start();
        printString("Hello from main");
    }
    static class PrintThread extends Thread {
        @Override
        public void run() {
            printString("Hello from thread");
        }
    }
    static class CountDown implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException exc) {

                }
            }
        }
    }
    static void printString(String value) {
        System.out.println(Thread.currentThread().getName() + ": " + value);
    }
}
