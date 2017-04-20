package com.github.xbest.locks.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by link on 2017/4/19.
 */
public class CountDownLacthTest {
    public static void main(String[] args) {
        CountDownLatch countDownLacth = new CountDownLatch(10);
        ExecutorService executorService = Executors.newCachedThreadPool();
        // boss 等大家干完活
//        executorService.execute(new CountDownLacthBoss(countDownLacth));
        // boss 给大家一秒钟的时间，干不完就不等了
        executorService.execute(new CountDownLacthBossTimeOut(countDownLacth));
        // 如果改成9个，则始终await
        for (int i = 0; i < 10; i++) {
            // 苦命的工人开始干活
            executorService.execute(new CountDownLatchWorker(String.valueOf(i), countDownLacth));
        }
        executorService.shutdown();
    }
}
