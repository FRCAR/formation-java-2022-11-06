package com.bigcorp.companies.concurrency;

import java.util.concurrent.TimeUnit;

public class SynchronizeWithJoinCorrection {

	public static void main(String[] args) throws InterruptedException {

		System.out.println("Démarrage Thread principal");
		
		LongTaskRunnable runnable = new LongTaskRunnable();
		Thread monThreadLance = new Thread(runnable);
		monThreadLance.start();
		//Join permet d'attendre que monThreadLance est terminé 
		//avant que le thread courant ne poursuive.
		monThreadLance.join();
		System.out.println("La valeur vaut : " + runnable.getValue());
		
	
		System.out.println("Fin Thread principal");
	}

	private static final class LongTaskRunnable implements Runnable {
		
		private int value;
		
		public int getValue() {
			return value;
		}

		@Override
		public void run() {
			System.out.println("Démarrage LongTaskRunnable");
			try {
				Thread.sleep(5000);
				// TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				return;
			}
			this.value = (int)(Math.random() * 100);

			System.out.println("Fin LongTaskRunnable");
		}
	}

}
