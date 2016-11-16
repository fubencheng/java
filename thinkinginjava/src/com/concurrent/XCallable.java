package com.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class XCallable {

	private static ExecutorService fixedPool = Executors.newFixedThreadPool(5);
	private static List<String> resp = new ArrayList<String>();

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			Future<String> future = fixedPool.submit(new PrintCallable("message id " + i));
			try {
				resp.add(future.get());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		fixedPool.submit(new Runnable() {

			@Override
			public void run() {
				System.out.println("Submit runnable task!!!");
			}

		});

		for (String msg : resp) {
			System.out.println(msg);
		}

		fixedPool.shutdown();
	}

}

class PrintCallable implements Callable<String> {
	private String msg;

	public PrintCallable(String msg) {
		this.msg = msg;
	}

	@Override
	public String call() throws Exception {
		System.out.println(Thread.currentThread().getName() + ", send msg : " + msg);
		return Thread.currentThread().getName() + ", receive msg : " + msg;
	}

}
