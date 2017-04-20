package com.github.xbest.locks.reentrantreadwritelock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by link on 2017/4/20.
 */
public class CachedData {
    private String data;
    private volatile boolean cacheValid;
    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();
    private CyclicBarrier cyclicBarrier;

    public CachedData(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    public void processData() {
        try {
            // 模拟所有线程同时开始
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        readLock.lock();
        if (!cacheValid) {
            // 在申请写锁之前，必须释放读锁，因为不支持锁升级，即读锁不能升级为写锁
            readLock.unlock();
            writeLock.lock();
            try {
                if (!cacheValid) {
                    data = String.format("[初始化数据]-[%s]：", Thread.currentThread().getName());
                    log(data);
                    cacheValid = true;
                }
                // 写锁降级为读锁，这样仍然确保当前线程直接获取到读锁
                readLock.lock();
            } finally {
                // 释放写锁，仍然持有读锁
                writeLock.unlock();
            }
        }
        try {
            // 模拟业务处理data
            log(String.format("[处理数据]-[%s]", Thread.currentThread().getName()));
        } finally {
            // 释放读锁
            readLock.unlock();
        }
    }

    private void log(String message) {
        System.out.println(message);
    }
}
