package com.bigcorp.companies.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorCorrection {

	public static void main(String[] args) throws Exception, ExecutionException {

		List<Callable<Integer>> callables = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			callables.add(new RandomIntegerCallable());
		}
		ExecutorService executorService = Executors.newFixedThreadPool(4);

		Integer first = executorService.invokeAny(callables);
		System.out.println("La valeur récupérée vaut : " + first);

		executorService.shutdown();

		System.out.println("Fin Thread principal");
	}

	private static final class RandomIntegerCallable implements Callable<Integer> {
		@Override
		public Integer call() {
			System.out.println("Démarrage RandomIntegerCallable");
			try {
				int sleepSeconds = 5 + (int) (5 * Math.random());
				TimeUnit.SECONDS.sleep(sleepSeconds);
				System.out.println("Fin RandomIntegerCallable");
				return (int) (Math.random() * 100);
			} catch (InterruptedException e) {
				return 0;
			}

		}
	}

}
