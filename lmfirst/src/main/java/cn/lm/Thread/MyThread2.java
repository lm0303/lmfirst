package cn.lm.Thread;

public class MyThread2 implements Runnable {
	private int i = 0;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (; i < 50; i++) {
			System.out.println("当前线程是：" + Thread.currentThread().getName() + i + ";");
		}
	}

	public static void main(String[] args) {
		MyThread2 myThread2 = new MyThread2();
		new Thread(myThread2,"线程一").start();
		//new Thread(myThread2,"线程一").run();
	}
}
