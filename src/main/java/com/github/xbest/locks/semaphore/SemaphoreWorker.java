package com.github.xbest.locks.semaphore;

import java.util.concurrent.Semaphore;

/**
 * Created by link on 2017/4/18.
 */
public class SemaphoreWorker implements Runnable {
    private Semaphore permits;
    private String name;

    public SemaphoreWorker(Semaphore permits, String name) {
        this.permits = permits;
        this.name = name;
    }

    @Override
    public void run() {
        System.out.printf("[启动]--线程%s启动，当前许可数：%s。%n", name, permits.availablePermits());
        try {
            permits.acquire();
            System.out.printf("[获取]--线程%s获得许可，开始业务处理，当前许可数：%s。%n", name, permits.availablePermits());
            // 模拟业务处理
            Thread.sleep(10000);
            System.out.printf("[处理]--线程%s处理完业务，当前许可数：%s。%n", name, permits.availablePermits());
            permits.release();
            System.out.printf("[释放]--线程%s释放许可，当前许可数：%s。%n", name, permits.availablePermits());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
