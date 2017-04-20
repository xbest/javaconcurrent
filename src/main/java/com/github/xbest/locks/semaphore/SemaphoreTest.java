package com.github.xbest.locks.semaphore;

import java.util.concurrent.*;

/**
 * Created by link on 2017/4/18.
 */
public class SemaphoreTest {
    private static final int PERMIT_NUM = 2;
    private static final Semaphore PERMITS = new Semaphore(PERMIT_NUM);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        try {
            for (int i = 0; i < 100; i++) {
                executorService.execute(new SemaphoreWorker(PERMITS, String.valueOf(i)));
            }
        } finally {
            executorService.shutdown();
        }
    }
}
