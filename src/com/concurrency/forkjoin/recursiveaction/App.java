package com.concurrency.forkjoin.recursiveaction;

import java.util.concurrent.ForkJoinPool;

public class App {
	public static void main(String[] args) {
		ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		SimpleRecursiveAction simpleRecursiveAction = new SimpleRecursiveAction(120);
		forkJoinPool.invoke(simpleRecursiveAction);
	}
}
