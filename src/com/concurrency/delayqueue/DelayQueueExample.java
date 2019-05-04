package com.concurrency.delayqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;

public class DelayQueueExample {
    public static void main(String[] args) {
        BlockingQueue<DelayWorker> delayQueue = new DelayQueue<>();
        try {
            delayQueue.put(new DelayWorker(1000, "First item"));
            delayQueue.put(new DelayWorker(10000, "Second item"));
            delayQueue.put(new DelayWorker(4000, "Third item"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (!delayQueue.isEmpty()) {
            try {
                System.out.println("Removing " + delayQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
