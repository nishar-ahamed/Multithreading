package com.concurrency.dinningphilosopherproblem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DinningPhilosopher {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(Constants.NUMBER_OF_PHILOSOPHERS);
        Philosopher[] philosophers = new Philosopher[Constants.NUMBER_OF_PHILOSOPHERS];
        Chopstick[] chopsticks = new Chopstick[Constants.NUMBER_OF_CHOPSTICKS];

        try {
            for (int i = 0; i < chopsticks.length; i++) {
                chopsticks[i] = new Chopstick(i);
            }
            for (int i = 0; i < philosophers.length; i++) {
                philosophers[i] = new Philosopher(i, chopsticks[i], chopsticks[(i + 1) % Constants.NUMBER_OF_CHOPSTICKS]);
            }
            for (int i = 0; i < philosophers.length; i++) {
                executorService.execute(philosophers[i]);
            }

            Thread.sleep(Constants.SIMULATION_RUNNING_TIME);

            for (Philosopher philosopher : philosophers) {
                philosopher.setFull(true);
            }

        } finally {
            executorService.shutdown();
            if (!executorService.isTerminated()) {
                Thread.sleep(1000);
            }
            for (Philosopher philosopher : philosophers) {
                System.out.println(philosopher + " eat #" + philosopher.getEatingCounter());
            }
        }
    }
}