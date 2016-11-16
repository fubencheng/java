package com.concurrent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class XThreadPool {

	private static ExecutorService singlePool = Executors.newSingleThreadExecutor();

	private static ExecutorService fixPool = Executors.newFixedThreadPool(5);

	private static ExecutorService cachedPool = Executors.newCachedThreadPool();

	public static void main(String[] args) {
		singlePool.execute(new Runnable() {
			public void run() {
				RandomAccessFile rdmAccessFile = null;
				try {
					rdmAccessFile = new RandomAccessFile("file/msg.txt", "rw");
					rdmAccessFile.seek(rdmAccessFile.length());
					rdmAccessFile.writeBytes(Thread.currentThread().toString() + ", hello world ~~~ ");
					rdmAccessFile.writeBytes("\n");
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						rdmAccessFile.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});

		try {
			TimeUnit.SECONDS.sleep(2L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < 10; i++) {
			fixPool.execute(new Runnable() {
				public void run() {
					System.out.println(Thread.currentThread().getName() + ", ~~~ ");
				}
			});
		}

		try {
			TimeUnit.SECONDS.sleep(2L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		singlePool.execute(new Runnable() {
			public void run() {
				FileWriter writer = null;
				try {
					writer = new FileWriter(new File("file/msg.txt"), true);
					writer.write(Thread.currentThread().toString() + ", hello JAVA ~~~ ");
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						writer.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});

		for (int i = 0; i < 5; i++) {
			cachedPool.execute(new Runnable() {
				public void run() {
					System.out.println(Thread.currentThread().getName() + ", !!! ");
				}
			});
		}

		singlePool.shutdown();
		fixPool.shutdown();
		cachedPool.shutdown();
	}

}
