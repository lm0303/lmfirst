package cn.lm.test;

import java.util.Scanner;

public class Main10 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		int[] a = new int[t];
		for (int i = 0; i < t; i++) {
			a[i] = sc.nextInt();
		}
		for (int i = 0; i < a.length; i++) {
			if (a[i]<10) {
				System.out.println(a[i]);
			}else {
				int num = 0;
				while(a[i]>=10){
					a[i] -=9;
					num++;
				}
				System.out.print(String.valueOf(a[i]));
				for (int j = 0; j < num; j++) {
					System.out.print(9);
					if (j+1 == num) {
						System.out.println();
					}
				}
			}
		}
	}
}
