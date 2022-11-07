package com.bigcorp.companies.concurrency;

import java.util.concurrent.TimeUnit;

public class SynchronizationIssuesCorrection {

	public static void main(String[] args) {

		System.out.println("Démarrage Thread principal");
		LongTaskRunnable runnable = new LongTaskRunnable();
		new Thread(runnable).start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		runnable.orderStop();
		
		
		
		
		System.out.println("Fin Thread principal");
		
		
	}

	private static final class LongTaskRunnable implements Runnable {

		private boolean stopOrdered = false;

		public synchronized boolean isStopOrdered() {
			return this.stopOrdered;
		}

		public synchronized void orderStop() {
			this.stopOrdered = true;
		}

		@Override
		public void run() {
			System.out.println("Démarrage LongTaskRunnable");
			int i = 0;
			while (!isStopOrdered()) {
				i++;
			}
			System.out.println("Fin LongTaskRunnable");
		}
	}

}
