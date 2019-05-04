package com.concurrency.concurrentmap;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

class FirstWorker implements Runnable {
    private ConcurrentMap<String, Integer> concurrentMap;

    public FirstWorker(ConcurrentMap<String, Integer> concurrentMap) {
        this.concurrentMap = concurrentMap;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            concurrentMap.put("F", 1);
            concurrentMap.put("D", 2);
            concurrentMap.put("A", 4);
            Thread.sleep(2000);
            concurrentMap.put("B", 7);
            concurrentMap.put("E", 9);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class SecondWorker implements Runnable {
    private ConcurrentMap<String, Integer> concurrentMap;

    public SecondWorker(ConcurrentMap<String, Integer> concurrentMap) {
        this.concurrentMap = concurrentMap;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
            System.out.println(concurrentMap.get("A"));
            System.out.println(concurrentMap.get("F"));
            Thread.sleep(3000);
            System.out.println(concurrentMap.get("E"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ConcurrentMapExample {
    public static void main(String[] args) {
        ConcurrentMap<String, Integer> concurrentMap = new ConcurrentHashMap<>();
        new Thread(new FirstWorker(concurrentMap)).start();
        new Thread(new SecondWorker(concurrentMap)).start();
    }
}
