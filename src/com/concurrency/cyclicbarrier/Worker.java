package com.concurrency.cyclicbarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Worker implements Runnable {
    private int id;
    private CyclicBarrier cyclicBarrier;
    private Random random;

    public Worker(int id, CyclicBarrier cyclicBarrier) {
        this.id = id;
        this.cyclicBarrier = cyclicBarrier;
        this.random = new Random();
    }

    private void doWork() {
        System.out.println("Starting thread with id " + id + " ...");
        try {
            Thread.sleep(random.nextInt(3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread with id " + id + " completed work...");
        try {
            cyclicBarrier.await();
            System.out.println("After Await of Thread id " + id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        doWork();
    }
}
