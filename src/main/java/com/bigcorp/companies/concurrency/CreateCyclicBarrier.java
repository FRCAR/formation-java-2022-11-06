package com.bigcorp.companies.concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CreateCyclicBarrier {

	public static void main(String[] args) throws InterruptedException {

		System.out.println("Démarrage Thread principal");
		CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Announcement());
		new Thread(new Runner(cyclicBarrier)).start();
		new Thread(new Runner(cyclicBarrier)).start();
		new Thread(new Runner(cyclicBarrier)).start();
		new Thread(new Runner(cyclicBarrier)).start();
		Thread.sleep(3000);
		System.out.println("Le dernier coureur arrive");
		new Thread(new Runner(cyclicBarrier)).start();
		System.out.println("Fin Thread principal");
	}

	private static final class Runner implements Runnable {

		private CyclicBarrier barrier;

		public Runner(CyclicBarrier barrier) {
			this.barrier = barrier;
		}

		@Override
		public void run() {
			System.out.println("Démarrage Runner");
			try {
				System.out.println("Le coureur se place sur la piste");
				this.barrier.await();
				System.out.println("Il court !");
				Thread.sleep((long) (Math.random() * 10000));
				System.out.println("Il arrive !");
			} catch (InterruptedException | BrokenBarrierException e) {
				return;
			}
			System.out.println("Fin Runner");
		}
	}

	private static final class Announcement implements Runnable {

		@Override
		public void run() {
			System.out.println("La course démarre.");
		}
	}

}
