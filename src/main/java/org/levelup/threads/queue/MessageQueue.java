package org.levelup.threads.queue;

public interface MessageQueue {
    <T> void put(MessageTask<T> messageTask);
    <T> MessageTask<T> take();
}
