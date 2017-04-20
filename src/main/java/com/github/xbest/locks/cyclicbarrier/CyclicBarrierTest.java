package com.github.xbest.locks.cyclicbarrier;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by link on 2017/4/20.
 */
public class CyclicBarrierTest {
    public static void main(String[] args) throws InterruptedException {
        int barrierNum = 5;
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(1);

        CyclicBarrier cyclicBarrier = new CyclicBarrier(barrierNum, () -> {
            System.out.println("哈哈，都到齐了，吃饭~");
            countDownLatch.countDown();
        });

        for (int i = 0; i < barrierNum; i++) {
            executorService.execute(new CyclicBarrierWorker(cyclicBarrier));
        }

        countDownLatch.await();
//      此处注释去掉后，CyclicBarrierWorker中的第二个await要注释掉。主要验证重置计数器现象。
//        for (int i = 0; i < barrierNum; i++) {
//            executorService.execute(new CyclicBarrierWorker(cyclicBarrier));
//        }

        executorService.shutdown();
    }
}
