package com.github.xbest.locks.reentrantreadwritelock;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 示例代码来源JDK1.8中的{@link java.util.concurrent.locks.ReentrantReadWriteLock}
 * Created by link on 2017/4/20.
 */
public class ReentrantReadWriteLockTest {
    public static void main(String[] args) {
        int threadNum = 100;
        ExecutorService executorService = Executors.newCachedThreadPool();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(threadNum);
        CachedData cachedData = new CachedData(cyclicBarrier);

        for (int i = 0; i < threadNum; i++) {
            executorService.execute(cachedData::processData);
        }

        executorService.shutdown();
    }
}
