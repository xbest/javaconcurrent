package com.github.xbest.locks.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by link on 2017/4/19.
 */
public class CountDownLatchWorker implements Runnable {
    private String name;
    private CountDownLatch countDownLatch;

    public CountDownLatchWorker(String name, CountDownLatch countDownLatch) {
        this.name = name;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        log("[Before-CountDownLatch]-[CountDownLatchCount:" + countDownLatch.getCount() + "]");
        sleep();
        countDownLatch.countDown();
        log("[After-CountDownLatch]-[CountDownLatchCount:" + countDownLatch.getCount() + "]");
    }

    private void log(String message) {
        System.out.println("[线程" + name + "]-" + message);
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
