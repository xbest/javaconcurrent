package com.github.xbest.locks.reentrantlock;

/**
 * Created by link on 2017/4/19.
 */
public class ReentrantLockWorker implements Runnable {
    private String name;
    private ReentrantLockExample reentrantLockExample;
    private boolean fair;

    public ReentrantLockWorker(String name, ReentrantLockExample reentrantLockExample, boolean fair) {
        this.name = name;
        this.reentrantLockExample = reentrantLockExample;
        this.fair = fair;
    }

    @Override
    public void run() {
        lockAndunlockMethod();
    }

    private void lockAndunlockMethod() {
        log("[BeforeLock]");
        if (fair) {
            reentrantLockExample.fairLockMethod();
        } else {
            reentrantLockExample.unfairLockMethod();
        }
        log("[AfterLock]");
        sleep();
        log("[BeforeUnLock]");
        if (fair) {
            reentrantLockExample.fairUnlockMethod();
        } else {
            reentrantLockExample.unfairUnlockMethod();
        }
        log("[AfterUnLock]");
    }

    private void sleep() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void log(String message) {
        System.out.println("[线程" + name + "]-" + message);
    }
}
