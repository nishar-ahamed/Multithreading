package com.concurrency.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample {

    private static int counter = 0;
    private static Lock lock = new ReentrantLock();

    public static void increment() throws InterruptedException {
        lock.lock();
        try {
            for (int i = 0; i < 10000; i++)
                ++counter;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    increment();
                } catch (InterruptedException e) {

                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    increment();
                } catch (InterruptedException e) {

                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Count : " + counter);
    }
}
