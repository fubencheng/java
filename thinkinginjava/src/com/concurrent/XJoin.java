package com.concurrent;

public class XJoin {

	public static void main(String[] args) {
		Sleeper sleepy = new Sleeper("Sleepy", 1500), 
				grumpy = new Sleeper("grumpy", 3000);

		new Joiner("Dopey", sleepy);
		new Joiner("Doc", grumpy);
		grumpy.interrupt();
	}

}

class Sleeper extends Thread {

	private int sleepTime;

	public Sleeper(String name, int sleepTime) {
		super(name);
		this.sleepTime = sleepTime;
		start();
	}

	public void run() {
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			System.out.println(
					Thread.currentThread().getName() + " was interrupted. isInterrupted() : " + isInterrupted());
			e.printStackTrace();
		}

		System.out.println(Thread.currentThread().getName() + " has awarkened");
	}
}

class Joiner extends Thread {
	private Sleeper sleeper;

	public Joiner(String name, Sleeper sleeper) {
		super(name);
		this.sleeper = sleeper;
		start();
	}

	public void run() {
		try {
			sleeper.join();
		} catch (InterruptedException e) {
			System.out.println("Interrupted!");
			e.printStackTrace();
		}

		System.out.println(Thread.currentThread().getName() + " join completed!");
	}
}