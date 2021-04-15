package cn.lm.test;

import java.util.Scanner;

public class Main6 {
	public static int factorial1(int n){
		if (n==0||n==1){
			return 1;
		}
		return factorial1(n-1)*n;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int num = factorial1(n);
		System.out.println(num);
		String string = String.valueOf(num);
		boolean flag = true;
		while (flag) {
			if (string.substring(string.length() -1).equals("0")) {
				string = string.substring(0, string.length()-1);
			}else {
				System.out.println(string.substring(string.length() -1));
				break;
			}
		}
	}
}
