package org.levelup.threads.queue;

public class App {
    public static void main(String[] args) {
        MessageQueue messageQueue = new SynchronizedMessageQueue(5);
        Thread producer1 = new Thread(new Producer(messageQueue), "producer_1");
        Thread producer2 = new Thread(new Producer(messageQueue), "producer_2");

        Thread consumer1 = new Thread(new Consumer(messageQueue), "consumer_1");
        Thread consumer2 = new Thread(new Consumer(messageQueue), "consumer_2");

        consumer1.start();
        consumer2.start();

        producer1.start();
        producer2.start();
    }
}
