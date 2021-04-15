package cn.lm.test;

import java.util.Scanner;

public class Main12 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		int[][] arr = new int[t][4];
		for (int i = 0; i < t; i++) {
			for (int j = 0; j < 4; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		for (int i = 0; i < t; i++) {
			int a = arr[i][0];
			int b = arr[i][1];
			int p = arr[i][2];
			int q = arr[i][3];
			int index = 1;
			while ((a + p) < b) {
				p *= q;
				index++;
			}
			System.out.println(index);
		}
	}
}
