package com.github.xbest.blockingqueue.arrayblockingqueue;

import java.util.concurrent.*;

/**
 * Created by link on 2017/5/12.
 */
public class ArrayBlockingQueueTest {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Producer(queue));
        executorService.execute(new Producer(queue));
        executorService.execute(new Consumer(queue));
        executorService.shutdown();
    }
}
