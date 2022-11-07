package com.bigcorp.companies.concurrency;

public class CreateNewThreadCorrection {

	public static void main(String[] args) throws InterruptedException {

		System.out.println("Démarrage Thread principal");
		// Ci-dessous, ne démarre pas un Thread
		//new LongTaskRunnable().run();
		Thread monThreadLance = new Thread(new LongTaskRunnable());
		monThreadLance.start();
		//Join permet d'attendre que monThreadLance est terminé 
		//avant que le thread courant ne poursuive.
		//monThreadLance.join();
		
		LongTaskThread longTaskThread = new LongTaskThread();
		longTaskThread.start();
		// longTaskThread.join();
		System.out.println("Fin Thread principal");
	}

	private static final class LongTaskRunnable implements Runnable {
		@Override
		public void run() {
			System.out.println("Démarrage LongTaskRunnable");
			try {
				Thread.sleep(2000);
				// TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				return;
			}

			System.out.println("Fin LongTaskRunnable");
		}
	}

	private static final class LongTaskThread extends Thread {
		@Override
		public void run() {
			System.out.println("Démarrage LongTaskThread");
			try {
				Thread.sleep(2000);
				// TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				return;
			}
			System.out.println("Fin LongTaskThread");
		}

	}

}
