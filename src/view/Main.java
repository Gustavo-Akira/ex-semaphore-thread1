package view;

import java.util.concurrent.Semaphore;

import model.CalculateThread;

public class Main {
	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(1);
		for(int x=1;x<=21;x++) {
			CalculateThread thread = new CalculateThread(x, semaphore);
			thread.start();
		}
	}
}
