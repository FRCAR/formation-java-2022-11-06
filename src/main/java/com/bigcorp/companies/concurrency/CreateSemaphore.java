package com.bigcorp.companies.concurrency;

import java.util.concurrent.Semaphore;

public class CreateSemaphore {

	public static void main(String[] args) {

		System.out.println("Démarrage Thread principal");
		Semaphore semaphore = new Semaphore(1, true);
		LongResource longResource = new LongResource();
		new Thread(new LongTaskRunnable(semaphore, longResource)).start();
		new Thread(new LongTaskRunnable(semaphore, longResource)).start();
		new Thread(new LongTaskRunnable(semaphore, longResource)).start();
		System.out.println("Fin Thread principal");
	}

	private static final class LongTaskRunnable implements Runnable {

		private Semaphore semaphore;
		private LongResource resource;

		public LongTaskRunnable(Semaphore semaphore, LongResource resource) {
			this.semaphore = semaphore;
			this.resource = resource;
		}

		@Override
		public void run() {
			System.out.println("Démarrage LongTaskRunnable");
			try {
				this.semaphore.acquire();
				System.out.println("Sémaphore passé !");
				this.resource.process();
			} catch (InterruptedException e) {
				return;
			} finally {
				this.semaphore.release();
			}
			System.out.println("Fin LongTaskRunnable");
		}
	}

	private static final class LongResource {

		public void process() {
			System.out.println("Démarrage process");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				return;
			}
			System.out.println("Fin process");
		}

	}

}
