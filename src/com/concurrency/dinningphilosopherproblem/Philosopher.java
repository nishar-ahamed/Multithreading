package com.concurrency.dinningphilosopherproblem;

import java.util.Random;

public class Philosopher implements Runnable {
    private int id;
    private Chopstick leftChopstick;
    private Chopstick rightChopstick;
    private volatile boolean isFull = false;
    private Random random;
    private int eatingCounter;

    public Philosopher(int id, Chopstick leftChopstick, Chopstick rightChopstick) {
        this.id = id;
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
        this.random = new Random();
    }

    public void think() throws InterruptedException {
        System.out.println(this + " is thinking...");
        Thread.sleep(this.random.nextInt(1000));
    }

    public void eat() throws InterruptedException {
        if (leftChopstick.pickUp(this, State.LEFT)) {
            if (rightChopstick.pickUp(this, State.RIGHT)) {
                System.out.println(this + " is eating...");
                this.eatingCounter++;
                Thread.sleep(this.random.nextInt(1000));
                rightChopstick.putDown(this, State.RIGHT);
            }
            leftChopstick.putDown(this, State.LEFT);
        }
    }

    public int getEatingCounter() {
        return eatingCounter;
    }

    public void setFull(boolean full) {
        isFull = full;
    }

    @Override
    public void run() {
        while (!isFull) {
            try {
                think();
                eat();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return "Philosopher-" + this.id;
    }
}
