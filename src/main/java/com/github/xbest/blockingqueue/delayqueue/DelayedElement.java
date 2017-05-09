package com.github.xbest.blockingqueue.delayqueue;

import java.util.Objects;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created by link on 2017/5/8.
 */
public class DelayedElement implements Delayed {
    /**
     * 延迟时间
     */
    private final long delay;
    /**
     * 过期时间
     */
    private final long expire;
    /**
     * 创建时间
     */
    private final long now;
    /**
     * 模拟存放的数据
     */
    private final String msg;

    /**
     * @param delay 延期时间，单位{@link TimeUnit#NANOSECONDS}
     * @param msg
     */
    public DelayedElement(long delay, String msg) {
        this.delay = delay;
        this.now = System.currentTimeMillis();
        this.expire = this.now + delay;
        this.msg = msg;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.expire - System.currentTimeMillis(), unit);
    }

    @Override
    public int compareTo(Delayed o) {
        if (o == this) {
            return 0;
        }
        if (o instanceof DelayedElement) {
            DelayedElement e = (DelayedElement) o;
            return (int) (this.delay - e.delay);
        }
        return 1;
    }

    @Override
    public String toString() {
        return "DelayedElement{" +
                "delay=" + delay +
                ", expire=" + expire +
                ", now=" + now +
                ", msg='" + msg + '\'' +
                '}';
    }
}
