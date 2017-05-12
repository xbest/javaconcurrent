package com.github.xbest.blockingqueue.arrayblockingqueue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * Created by link on 2017/5/12.
 */
public class Producer implements Runnable {
    private BlockingQueue<Integer> queue;

    public Producer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            Random random = new Random();
            try {
                Thread.sleep(2000);
                int i = random.nextInt();
                queue.put(i);
                System.out.printf("线程：%s，插入值：%s\n", Thread.currentThread().getName(), i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
