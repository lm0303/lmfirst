package cn.lm.Thread;

public class MyThread1 extends Thread {
	private int i = 0;

	@Override
	public void run() {
		for (; i < 50; i++) {
			System.out.println("当前线程是：" + Thread.currentThread().getName() + i + ";");
		}
	}

	public static void main(String[] args) {
		new MyThread1().start();
		//new MyThread1().run();
	}
}
