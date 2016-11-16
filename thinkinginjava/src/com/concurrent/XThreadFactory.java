package com.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class XThreadFactory {

	public static void main(String[] args) {
		ExecutorService pool = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			Thread t = PrintThreadFactory.getSingtonPrintThreadFactory().newThread(new PrintThread("Hello world"));
			pool.execute(t);
		}

		pool.shutdown();

		pool = Executors.newCachedThreadPool(PrintThreadFactory.getSingtonPrintThreadFactory());
		for (int i = 0; i < 10; i++) {
			pool.execute(new PrintThread("Hello world!!!"));
		}

		pool.shutdown();
	}

	static class PrintThread implements Runnable {

		private String printMsg;

		public PrintThread(String printMsg) {
			this.printMsg = printMsg;
		}

		public void run() {
			System.out.println(Thread.currentThread().getName() + " , " + printMsg);
			try {
				TimeUnit.MICROSECONDS.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	static class PrintThreadFactory implements ThreadFactory {

		private static ThreadFactory printThreadFactory = null;

		private PrintThreadFactory() {

		}

		public static final synchronized ThreadFactory getSingtonPrintThreadFactory() {
			if (printThreadFactory == null) {
				printThreadFactory = new PrintThreadFactory();
			}

			return printThreadFactory;
		}

		public Thread newThread(Runnable r) {

			return new Thread(r);
		}

	}
}
