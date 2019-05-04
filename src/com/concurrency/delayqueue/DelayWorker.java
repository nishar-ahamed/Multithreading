package com.concurrency.delayqueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayWorker implements Delayed {
    private long duration;
    private String message;

    public DelayWorker(long duration, String message) {
        this.duration = System.currentTimeMillis() + duration;
        this.message = message;
    }

    public long getDuration() {
        return duration;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(duration - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        if (this.duration < ((DelayWorker) o).getDuration())
            return -1;
        if (this.duration > ((DelayWorker) o).getDuration())
            return 1;
        return 0;

    }

    @Override
    public String toString() {
        return this.message;
    }
}
