package com.github.xbest.locks.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by link on 2017/4/19.
 */
public class ReentrantLockExample {
    private ReentrantLock unfairLock = new ReentrantLock();
    private ReentrantLock fairLock = new ReentrantLock(true);

    public void unfairLockMethod() {
        unfairLock.lock();
    }

    public void unfairUnlockMethod() {
        unfairLock.unlock();
    }

    public void fairLockMethod() {
        fairLock.lock();
    }
    public void fairUnlockMethod() {
        fairLock.unlock();
    }
}
