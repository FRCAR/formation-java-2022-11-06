package com.bigcorp.companies.concurrency;

import java.util.concurrent.TimeUnit;

public class SynchronizationIssuesCounterCorrection {

	public static void main(String[] args) throws InterruptedException {

		System.out.println("Démarrage Thread principal");
		Counter counter = new Counter();

		CounterUserRunnable runnable1 = new CounterUserRunnable(counter);
		CounterUserRunnable runnable2 = new CounterUserRunnable(counter);
		Thread thread1 = new Thread(runnable1);
		thread1.start();
		Thread thread2 = new Thread(runnable2);
		thread2.start();

		thread1.join();
		thread2.join();
		System.out.println("La valeur du compteur de runnable1 vaut : " + runnable1.getValue());
		System.out.println("La valeur du compteur de runnable2 vaut : " + runnable2.getValue());

		System.out.println("Fin Thread principal");

	}

	private static final class CounterUserRunnable implements Runnable {

		private Counter counter;
		private int value;

		public int getValue() {
			return value;
		}

		public CounterUserRunnable(Counter counter) {
			super();
			this.counter = counter;
		}

		@Override
		public void run() {
			System.out.println("Démarrage LongTaskRunnable");
			this.value = counter.incrementAndGet();
			System.out.println("Fin LongTaskRunnable");
		}
	}

	private static final class Counter {

		private int counter = 0;

		public synchronized int incrementAndGet() {
			try {
				TimeUnit.SECONDS.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return counter++;
		}

		public synchronized int decrementAndGet() {
			return counter--;
		}

		public synchronized int value() {
			return counter;
		}

		public void increment() {
			try {
				TimeUnit.SECONDS.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			counter++;
		}
	}

}
