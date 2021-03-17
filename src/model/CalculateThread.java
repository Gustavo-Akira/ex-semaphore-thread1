package model;

import java.util.concurrent.Semaphore;

public class CalculateThread extends Thread{
	private int id;
	private Semaphore semaphore;
	public CalculateThread(int id, Semaphore semaphore) {
		this.id = id;
		this.semaphore = semaphore;
	}
	@Override
	public void run() {
		main();
		interrupt();
	}
	private void main() {
		long timeOfSleep = 0L;
		long timeOfBD = 0L;
		timeOfSleep = timeCaculating();
		timeOfBD = timeOfTransaction();
		try {
			calculatingAction(timeOfSleep);
			transactionAction(timeOfBD);
			timeOfSleep = timeCaculating();
			calculatingAction(timeOfSleep);
			transactionAction(timeOfBD);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	private long timeCaculating() {
		if(id%3== 1) {
			return (long)(200 + Math.random()*1000);
		}else if(id%3==2) {
			return (long)(500 + Math.random() * 1500);
		}else if(id%3 == 0) {
			return (long)(1000 + Math.random()*2000);
		}
		return 0L;
	}
	private long timeOfTransaction() {
		if(id%3== 1) {
			return 1000L;
		}else if(id%3==2) {
			return 1500;
		}else if(id%3 == 0) {
			return 1500;
		}
		return 0L;
	}
	private void transactionAction(long timeOfBD) throws InterruptedException {
		semaphore.acquire();
		System.out.println(" a thread " + id + " esta fazendo a transação do BD por " + ((double)timeOfBD/1000));
		sleep(timeOfBD);
		semaphore.release();
	}
	private void calculatingAction(long timeOfSleep) throws InterruptedException {
		System.out.println(" A thread " + id + " esta calculando por " + ((double)timeOfSleep/1000));
		sleep(timeOfSleep);
	}
}
