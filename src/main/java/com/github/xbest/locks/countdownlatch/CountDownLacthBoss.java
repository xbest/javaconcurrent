package com.github.xbest.locks.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by link on 2017/4/19.
 */
public class CountDownLacthBoss implements Runnable {
    private CountDownLatch countDownLatch;

    public CountDownLacthBoss(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            System.out.println("大家抓紧commit，等你们commit了，我deploy啊。");
            countDownLatch.await();
            System.out.println("擦，等了大半年，你们终于commit了，我开始deploy。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
