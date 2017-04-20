package com.github.xbest.locks.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by link on 2017/4/19.
 */
public class CountDownLacthBossTimeOut implements Runnable {
    private CountDownLatch countDownLatch;

    public CountDownLacthBossTimeOut(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            System.out.println("大家抓紧commit，等你们commit了，我deploy啊。");
            if (countDownLatch.await(1000, TimeUnit.MILLISECONDS)) {
                System.out.println("擦，等了大半年，你们终于commit了，我开始deploy。");
            } else {
                System.out.println("擦，都晚上十二点了，还没commit，老子不等了，下班走人了。");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
