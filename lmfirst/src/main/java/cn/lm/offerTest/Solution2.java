package cn.lm.offerTest;

import java.util.Scanner;

public class Solution2 {
	public static boolean isJump(int[] arr){
		boolean isjump = false;
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum +=arr[i];
		}
		if (sum >arr.length) {
			isjump = true;
		}
		return isjump;
	}
	public static void main(String[] args) {
		int[] s = new int[3];
		Scanner scanner = new Scanner(System.in);
		for (int i = 0; i < s.length; i++) {
			s[i] = scanner.nextInt();
		}
		System.out.print(isJump(s));
	}
}
