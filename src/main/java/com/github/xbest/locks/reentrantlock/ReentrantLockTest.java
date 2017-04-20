package com.github.xbest.locks.reentrantlock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by link on 2017/4/19.
 */
public class ReentrantLockTest {
    public static void main(String[] args) {
        ReentrantLockExample reentrantLockExample = new ReentrantLockExample();
        ExecutorService executorService = Executors.newCachedThreadPool();

        try {
            for (int i = 0; i < 100; i++) {
                executorService.execute(new ReentrantLockWorker(String.valueOf(i), reentrantLockExample,true));
            }
        } finally {
            executorService.shutdown();
        }
    }


}
