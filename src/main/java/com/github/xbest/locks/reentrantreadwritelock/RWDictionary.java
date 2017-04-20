package com.github.xbest.locks.reentrantreadwritelock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by link on 2017/4/20.
 */
public class RWDictionary {
    private final Map<String, String> stringMap = new HashMap<>();
    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();

    public String get(String key) {
        readLock.lock();
        String value;
        try {
            value = stringMap.get(key);
            log(String.format("[GET]-[key:%s, value:%s]", key, value));
        } finally {
            readLock.unlock();
        }
        return value;
    }

    public void put(String key, String value) {
        writeLock.lock();
        try {
            stringMap.put(key, value);
            log(String.format("[PUT]-[key:%s, value:%s]", key, value));
        } finally {
            writeLock.unlock();
        }
    }

    public String[] allKeys() {
        readLock.lock();
        try {
            return (String[]) stringMap.keySet().toArray();
        } finally {
            readLock.unlock();
        }
    }

    public void clead() {
        writeLock.lock();
        try {
            stringMap.clear();
        } finally {
            writeLock.unlock();
        }
    }

    private void log(String message) {
        System.out.println(String.format("[%s]-%s", Thread.currentThread().getName(), message));
    }
}
