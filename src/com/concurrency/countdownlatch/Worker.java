package com.concurrency.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class Worker implements Runnable {
    private int id;
    private CountDownLatch countDownLatch;

    public Worker(int id, CountDownLatch countDownLatch) {
        this.id = id;
        this.countDownLatch = countDownLatch;
    }

    public void doWork() {
        System.out.println("Thread with id " + id + " working....");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        doWork();
        countDownLatch.countDown();
    }
}
