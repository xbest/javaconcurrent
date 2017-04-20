package com.github.xbest.locks.synchronize;

/**
 * Created by link on 2017/4/19.
 */
public class SynchronizedExample {
    private byte[] lock = new byte[1];

    public synchronized static void staticMethod1() {
        logAndSleep("staticMethod1");
    }

    public synchronized static void staticMethod2() {
        logAndSleep("staticMethod2");
    }

    public synchronized void normalMethod1() {
        logAndSleep("normalMethod1");
    }

    public synchronized void normalMethod2() {
        logAndSleep("normalMethod2");
    }

    public void objectMethod1() {
        synchronized (lock) {
            logAndSleep("objectMethod1");
        }
    }

    public void objectMethod2() {
        synchronized (lock) {
            logAndSleep("objectMethod2");
        }
    }

    private static void logAndSleep(String message) {
        System.out.println(message);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
