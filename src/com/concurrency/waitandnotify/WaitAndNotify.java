package com.concurrency.waitandnotify;

public class WaitAndNotify {

    private final Object lock = new Object();

    public void producer() throws InterruptedException {
        synchronized (lock) {
            System.out.println("In producer method.....");
            lock.wait();
            System.out.println("Again in producer method.....");
        }
    }

    public void consumer() throws InterruptedException {
        Thread.sleep(1000);
        synchronized (lock) {
            System.out.println("In consumer method....");
            lock.notify(); // even after notify is called below code will first execute then after that waiting thread will execute
            System.out.println("Again in consumer method....");
        }
    }

    public static void main(String[] args) {
        WaitAndNotify waitAndNotify = new WaitAndNotify();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    waitAndNotify.producer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    waitAndNotify.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
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
    }
}
