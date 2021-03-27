package org.levelup.threads.sync;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockCounter implements Counter{
    private final ReentrantLock counterLock = new ReentrantLock();
    private int value;

    @Override
    public void increment() {
        counterLock.lock(); //взятие блокировки (ожидание взятия блокировки)
        try {
            value++;
        }
        finally {
            counterLock.unlock();
        }
    }

    @Override
    public int getValue() {
        counterLock.lock();
        try {
            return value;
        }
        finally {
            counterLock.unlock();
        }
    }
}
