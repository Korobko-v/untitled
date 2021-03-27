package org.levelup.threads.queue;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class Consumer implements Runnable{

    private final MessageQueue messageQueue;

    @Override
    @SneakyThrows
    public void run() {
        while (true) {
            MessageTask<Object> task = messageQueue.take();
            System.out.println(Thread.currentThread().getName() + ": " + task.process());

            Thread.sleep(500);
        }
    }
}
