package cn.lm.Thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class MyThread3 {
	public static void main(String[] args) {
		MyThread3 myThread3 = new MyThread3();
		FutureTask<Integer> task = new FutureTask<>((Callable<Integer>) () -> {
			int i = 0;
			for (; i < 50; i++) {
				System.out.println("当前线程是：" + Thread.currentThread().getName() + i + ";");
			}
			return i;
		});
		new Thread(task, "有返回值的线程").start();
	}
}
