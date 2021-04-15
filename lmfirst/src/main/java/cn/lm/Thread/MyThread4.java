package cn.lm.Thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class MyThread4 {
	public static void main(String[] args) {
		// 创建一个固定数目的线程池
		// ExecutorService pool = Executors.newFixedThreadPool(5);
		// 创建一个可缓存的线程池，调用execute方法将重用以前创建的线程，如果没有可用线程则创建一个新的线程并添加到池中。终止并移除那些已经存在60s未被使用的线程。
		// ExecutorService pool = Executors.newCachedThreadPool();
		// 创建一个单线程化的Excutor
		// ExecutorService pool = Executors.newSingleThreadExecutor();
		// 创建一个定时及周期性执行任务的线程池
		ExecutorService pool = Executors.newScheduledThreadPool(5);
		FutureTask<Integer> task = (FutureTask<Integer>) pool.submit(new Callable<Integer>() {
			int i = 0;

			@Override
			public Integer call() {
				for (; i < 50; i++) {
					System.out.println("当前线程是：" + Thread.currentThread().getName() + i + ";");
				}
				return i;
			}
		});
		pool.shutdown();
	}
}
