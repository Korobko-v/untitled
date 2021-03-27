package org.levelup.threads.sync;

import java.util.concurrent.atomic.AtomicInteger;

public class LockFreeCounter implements Counter {

    private AtomicInteger value = new AtomicInteger(0);

    @Override
    public void increment() {
        value.incrementAndGet();
    }

    @Override
    public int getValue() {
        return value.get();
    }
}
