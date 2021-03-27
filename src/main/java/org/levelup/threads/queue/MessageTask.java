package org.levelup.threads.queue;

public interface MessageTask<T> {
    T process();
}
