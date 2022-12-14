package com.bigcorp.companies.concurrency;

import java.util.concurrent.TimeUnit;

public class SynchronizeThreadWithVolatile {

	public static void main(String[] args) throws InterruptedException {

		System.out.println("Démarrage Thread principal");
		LongTaskRunnable runnable = new LongTaskRunnable();
		new Thread(runnable).start();
		TimeUnit.SECONDS.sleep(2);
		
		runnable.shouldStop2 = true;
		System.out.println("Fin Thread principal");
	}

	private static final class LongTaskRunnable implements Runnable {

		public boolean shouldStop2;

		@Override
		public void run() {
			System.out.println("Démarrage LongTaskRunnable");
			int i = 0;
			while (!shouldStop2) {
				i++;
			}
			System.out.println("Fin LongTaskRunnable");
		}

	}

}
