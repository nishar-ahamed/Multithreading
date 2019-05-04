package com.concurrency.blockingqueue;

import java.util.concurrent.BlockingQueue;

public class SecondWorker implements Runnable {
    private BlockingQueue<Integer> blockingQueue;

    public SecondWorker(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int num = blockingQueue.take();
                System.out.println("Removing item from queue..." + num);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
