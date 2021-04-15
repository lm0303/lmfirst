package cn.lm.test;

import java.util.Scanner;

public class Main4 {
	static void Preorder(String inorder, String postorder) {
		if (inorder.length() > 0) {
			char ch = postorder.charAt(postorder.length() - 1);
			System.out.print(ch);
			int k = inorder.indexOf(ch);
			Preorder(inorder.substring(0, k), postorder.substring(0, k));
			Preorder(inorder.substring(k + 1), postorder.substring(k, inorder.length() - k - 1));
		}
	}

	public static void main(String[] args) {
		String inorder = "DBGEACF", postorder = "DGEBFCA";
		/*Scanner sc = new Scanner(System.in);
		inorder = sc.nextLine();
		postorder = sc.nextLine();*/
		Preorder(inorder, postorder);
	}
}
