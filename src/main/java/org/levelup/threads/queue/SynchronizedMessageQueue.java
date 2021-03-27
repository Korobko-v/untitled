package org.levelup.threads.queue;

import lombok.SneakyThrows;

import java.util.LinkedList;

public class SynchronizedMessageQueue implements MessageQueue {

    private final LinkedList<MessageTask<?>> queue = new LinkedList<>();
    private final Object fullCondition = new Object();
    private final Object emptyCondition = new Object();
    private final int size;

    public SynchronizedMessageQueue(int size) {
        this.size = size;
    }

    @Override
    @SneakyThrows
    public <T> void put(MessageTask<T> messageTask) {
        synchronized (fullCondition) {
            while (queue.size() >= size) {
                fullCondition.wait();
            }
        }
        synchronized (emptyCondition) {
            queue.addLast(messageTask);
            emptyCondition.notify();
        }
    }

    @Override
    @SneakyThrows
    public <T> MessageTask <T> take() {
        synchronized (emptyCondition) {
            while (queue.isEmpty()) {
                emptyCondition.wait();
            }
        }

        synchronized ((fullCondition)) {
            MessageTask<T> task = (MessageTask<T>) queue.poll();
            fullCondition.notifyAll();
            return task;
        }
    }

}
