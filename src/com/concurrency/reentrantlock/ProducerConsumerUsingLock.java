package com.concurrency.reentrantlock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerUsingLock {
    private final int LIMIT = 5;
    private final int BELOW = 0;
    private int value = 0;
    private List<Integer> list = new ArrayList<>();
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void producer() throws InterruptedException {
        lock.lock();
        try {
            while (true) {
                if (list.size() == LIMIT) {
                    System.out.println("Waiting for removing item from list ");
                    condition.await();
                } else {
                    System.out.println("Adding to list value " + value);
                    list.add(value);
                    ++value;
                    condition.signal();
                }
                Thread.sleep(100);
            }
        } finally {
            lock.unlock();
        }
    }

    public void consumer() throws InterruptedException {
        lock.lock();
        try {
            while (true) {
                if (list.size() == BELOW) {
                    System.out.println("Waiting for adding item to list");
                    condition.await();
                } else {
                    System.out.println("Removing from list value " + list.remove(--value));
                    condition.signal();
                }
                Thread.sleep(100);
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ProducerConsumerUsingLock producerConsumerUsingLock = new ProducerConsumerUsingLock();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    producerConsumerUsingLock.producer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    producerConsumerUsingLock.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }

}
