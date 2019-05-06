package com.concurrency.forkjoin.recursivetask;

import java.util.concurrent.RecursiveTask;

public class SimpleRecursiveTask extends RecursiveTask<Integer> {
	private int simulation;

	public SimpleRecursiveTask(int simulation) {
		this.simulation = simulation;
	}

	@Override
	protected Integer compute() {
		if (simulation > 100) {
			System.out.println("Parallel execution and split task..." + simulation);
			SimpleRecursiveTask simpleRecursiveTask1 = new SimpleRecursiveTask(simulation / 2);
			SimpleRecursiveTask simpleRecursiveTask2 = new SimpleRecursiveTask(simulation / 2);

			simpleRecursiveTask1.fork();
			simpleRecursiveTask2.fork();

			int result = 0;
			result += simpleRecursiveTask1.join();
			result += simpleRecursiveTask2.join();

			return result;
		} else {
			System.out.println("Sequencial execution..." + simulation);
			return 2 * simulation;
		}
	}
}
