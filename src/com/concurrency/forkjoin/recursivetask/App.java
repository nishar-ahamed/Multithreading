package com.concurrency.forkjoin.recursivetask;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class App {
	public static void main(String[] args) {
		ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		SimpleRecursiveTask simpleRecursiveTask = new SimpleRecursiveTask(20);
		forkJoinPool.invoke(simpleRecursiveTask);
	}
}
