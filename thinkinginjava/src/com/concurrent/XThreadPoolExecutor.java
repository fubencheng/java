package com.concurrent;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class XThreadPoolExecutor {

	public static void main(String[] args) {
		PrintThreadPoolExecutor poolExecutor = new PrintThreadPoolExecutor();
		for (int i = 0; i < 10; i++) {
			poolExecutor.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println("Hello world ~~~");

					try {
						Thread.sleep(1000L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}

		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		while (true) {
			if (poolExecutor.getCompletedTaskCount() != 10) {
				System.out.println("Active count = " + poolExecutor.getThreadFactory());
			}

			System.out.println("BREAK!!!");
			break;
		}

		poolExecutor.shutdown();
	}

	static class PrintThreadPoolExecutor extends ThreadPoolExecutor {

		public PrintThreadPoolExecutor() {
			super(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(),
					PrintThreadFactory.getSingtonPrintThreadFactory());
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
