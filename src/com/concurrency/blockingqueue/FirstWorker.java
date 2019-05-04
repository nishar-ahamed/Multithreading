package com.concurrency.blockingqueue;

import java.util.concurrent.BlockingQueue;

public class FirstWorker implements Runnable {
    private BlockingQueue<Integer> blockingQueue;

    public FirstWorker(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        int count = 0;
        while (true) {
            try {
                System.out.println("Adding item to queue..." + count);
                blockingQueue.put(count);
                count++;
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
