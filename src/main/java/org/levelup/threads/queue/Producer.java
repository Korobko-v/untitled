package org.levelup.threads.queue;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class Producer implements Runnable{

    private final MessageQueue messageQueue;

    @Override
    @SneakyThrows
    public void run() {
        for (int i = 0; i < 20; i++) {
            String threadName = Thread.currentThread().getName();
            int taskNumber = i;
            messageQueue.put(() -> {
                return threadName + " created " + taskNumber + "task for consumer";
            });
            System.out.println(threadName + " finished " + taskNumber + " task creation");
            Thread.sleep(300);
        }
    }
}
