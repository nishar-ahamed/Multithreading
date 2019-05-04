package com.concurrency.producerconsumer;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumer {

    private final int LIMIT = 5;
    private final int BELOW = 0;
    private int value = 0;
    private List<Integer> list = new ArrayList<>();
    private Object lock = new Object();

    public void producer() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                if (list.size() == LIMIT) {
                    System.out.println("Waiting for removing item from list ");
                    lock.wait();
                } else {
                    System.out.println("Adding to list value " + value);
                    list.add(value);
                    ++value;
                    lock.notify();
                }
                Thread.sleep(100);
            }
        }
    }

    public void consumer() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                if (list.size() == BELOW) {
                    System.out.println("Waiting for adding item to list");
                    lock.wait();
                } else {
                    System.out.println("Removing from list value " + list.remove(--value));
                    lock.notify();
                }
                Thread.sleep(100);
            }
        }
    }

    public static void main(String[] args) {
        ProducerConsumer producerConsumer = new ProducerConsumer();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    producerConsumer.producer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    producerConsumer.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }
}
