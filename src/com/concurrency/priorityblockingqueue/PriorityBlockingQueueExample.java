package com.concurrency.priorityblockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

class FirstWorker implements Runnable {
    private BlockingQueue<String> blockingQueue;

    public FirstWorker(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            blockingQueue.put("E");
            blockingQueue.put("S");
            blockingQueue.put("A");
            blockingQueue.put("C");
            blockingQueue.put("I");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class SecondWorker implements Runnable {
    private BlockingQueue<String> blockingQueue;

    public SecondWorker(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class PriorityBlockingQueueExample {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new PriorityBlockingQueue<>();
        new Thread(new FirstWorker(blockingQueue)).start();
        new Thread(new SecondWorker(blockingQueue)).start();
    }
}
