package com.concurrent;

public class XThread {

	public static void main(String[] args) {
		new InnerThread("InnerThread");
		new AnonymousInnerThread("AnonymousInnerThread");
		new InnerRunnable("InnerRunnable");
		new AnonymousInnerRunnable("AnonymousInnerRunnable");
	}
}

class InnerThread {

	public InnerThread(String name) {
		new Inner(name);
	}

	private class Inner extends Thread {
		public Inner(String name) {
			super(name);
			start();
		}

		public void run() {
			System.out.println(Thread.currentThread().getName() + "---1");
		}
	}
}

class AnonymousInnerThread {

	public AnonymousInnerThread(String name) {
		Thread t = new Thread(name) {
			public void run() {
				System.out.println(Thread.currentThread().getName() + "---2");
			}
		};

		t.start();
	}
}

class InnerRunnable {
	public InnerRunnable(String name) {
		new Inner(name);
	}

	private class Inner implements Runnable {
		public Inner(String name) {
			new Thread(this, name).start();
		}

		public void run() {
			System.out.println(Thread.currentThread().getName() + "---3");
		}
	}
}

class AnonymousInnerRunnable {

	public AnonymousInnerRunnable(String name) {
		new Thread(new Runnable() {
			public void run() {
				System.out.println(Thread.currentThread().getName() + "---4");
			}
		}, name).start();
	}

}