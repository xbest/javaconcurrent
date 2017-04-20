package com.github.xbest.locks.synchronize;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by link on 2017/4/19.
 */
public class SynchronizedTest {
    public static void main(String[] args) {
        SynchronizedExample synchronizedExample = new SynchronizedExample();
        ExecutorService executorService = Executors.newCachedThreadPool();
        try {
            // synchronized static method
            executorService.execute(SynchronizedExample::staticMethod1);
            executorService.execute(SynchronizedExample::staticMethod2);

            // synchronized normal method
            executorService.execute(synchronizedExample::normalMethod1);
            executorService.execute(synchronizedExample::normalMethod2);

            // synchronized object method
            executorService.execute(synchronizedExample::objectMethod1);
            executorService.execute(synchronizedExample::objectMethod2);
        } finally {
            executorService.shutdown();
        }

    }
}
