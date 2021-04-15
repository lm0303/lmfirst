package cn.lm.test;

import java.util.Scanner;

public class Main7 {
	public static boolean isPhone(String phone) {
		if (phone != null) {
			if (phone.length() == 11) {
				if (phone.charAt(0) == '8') {
					return true;
				} else {
					return false;
				}
			} else if (phone.length() > 11) {
				if (phone.charAt(0) == '8') {
					return true;
				} else {
					boolean isPhone = true;
					while (isPhone) {
						phone = phone.substring(1);
						if (phone.length() == 11 && phone.charAt(0) == '8') {
							return true;
						}
						if (phone.length() <= 11 && phone.charAt(0) != '8') {
							return false;
						}
					}
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		int[] lens = new int[t];
		String[] phones = new String[t];
		for (int i = 0; i < t; i++) {
			lens[i] = sc.nextInt();
			phones[i] = sc.next();
		}
		for (int i = 0; i < t; i++) {
			phones[i] = sc.next();
		}
		for (int i = 0; i < t; i++) {
			if (phones[i].length() < 11) {
				System.out.println("NO");
			} else {
				if (isPhone(phones[i])) {
					System.out.println("YES");
				} else {
					System.out.println("NO");
				}
			}
		}
	}
}
