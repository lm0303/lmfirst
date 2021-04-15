package cn.lm.test;

import java.util.Scanner;

public class Main9 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] a = new int[n];
		int[] b = new int[n];
		int[][] c = new int[n][n];
		for (int i = 0; i < n; i++) {
			a[i] = sc.nextInt();
		}
		for (int i = 0; i < n; i++) {
			b[i] = sc.nextInt();
		}
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b.length; j++) {
				c[i][j] = a[i]*b[j];
			}
		}
		int sum = 0;
		for (int i = 0; i < a.length; i++) {
			int sums[] = new int[n];
			sums[0] = c[i][0];
			for (int j = 0; j < b.length; j++) {
				sums[0] = sums[0]^c[i][j+1];
			}
		}
	}
}
