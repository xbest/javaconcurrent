package com.github.xbest.locks.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by link on 2017/4/20.
 */
public class CyclicBarrierWorker implements Runnable {
    private CyclicBarrier cyclicBarrier;

    public CyclicBarrierWorker(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            log("老子到了，等你们哈~");
            cyclicBarrier.await();
            log("吃完饭了，继续下一段路程~");
            cyclicBarrier.await();
            log("徒步完成全路段~");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    private void log(String message) {
        System.out.println("[" + Thread.currentThread().getName() + "]-" + message);
    }
}
