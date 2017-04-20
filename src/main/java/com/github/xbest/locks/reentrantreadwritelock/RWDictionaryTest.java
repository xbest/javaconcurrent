package com.github.xbest.locks.reentrantreadwritelock;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by link on 2017/4/20.
 */
public class RWDictionaryTest {
    public static void main(String[] args) {
        int num = 100;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(num * 2);
        RWDictionary dictionary = new RWDictionary();
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < num; i++) {
            String key = String.valueOf(i);
            String value = String.valueOf(i);
            executorService.execute(() -> {
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                Random random = new Random();
                int randomInt = random.nextInt(30);
                if (randomInt % 2 == 0) {
                    dictionary.put(key, value);
                } else {
                    dictionary.get(String.valueOf(randomInt));
                }
            });
        }

        for (int i = num - 1; i >= 0; i--) {
            String key = String.valueOf(i);
            executorService.execute(() -> {
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                dictionary.get(key);
            });
        }

        executorService.shutdown();
    }

}
