package com.concurrency.forkjoin.recursiveaction;

import java.util.concurrent.RecursiveAction;

public class SimpleRecursiveAction extends RecursiveAction {
	private int simulation;

	public SimpleRecursiveAction(int simulation) {
		this.simulation = simulation;
	}

	@Override
	protected void compute() {
		if (simulation > 100) {
			System.out.println("Parallel execution and split task..." + simulation);
			SimpleRecursiveAction simpleRecursiveAction1 = new SimpleRecursiveAction(simulation / 2);
			SimpleRecursiveAction simpleRecursiveAction2 = new SimpleRecursiveAction(simulation / 2);

			simpleRecursiveAction1.fork();
			simpleRecursiveAction2.fork();
		} else {
			System.out.println("Sequencial execution task..." + simulation);
		}
	}

}
