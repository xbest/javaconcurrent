package com.github.xbest.blockingqueue.delayqueue;

import java.util.concurrent.DelayQueue;

/**
 * Created by link on 2017/5/8.
 */
public class DelayQueueTest {
    public static void main(String[] args) {
        DelayQueue<DelayedElement> queue = new DelayQueue<>();
        DelayedElement e1 = new DelayedElement(10000, "10000 毫秒");
        DelayedElement e2 = new DelayedElement(15000, "15000 毫秒");
        DelayedElement e3 = new DelayedElement(5000, "5000 毫秒");
        queue.add(e1);
        queue.add(e2);
        queue.add(e3);
        while (queue.size() > 0) {
            try {
                System.out.println(queue.take().toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
