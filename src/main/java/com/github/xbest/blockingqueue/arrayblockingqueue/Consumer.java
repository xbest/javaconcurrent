package com.github.xbest.blockingqueue.arrayblockingqueue;

import java.util.concurrent.BlockingQueue;

/**
 * Created by link on 2017/5/12.
 */
public class Consumer implements Runnable {
    private BlockingQueue<Integer> queue;

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                int i = queue.take();
                System.out.printf("线程：%s，取出值：%s\n", Thread.currentThread().getName(), i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
