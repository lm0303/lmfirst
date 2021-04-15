package cn.lm.test;

public class Solution {
	public static void lengthOfLongestSubstring(String str) {

		String tmp = "";// 定义一个临时字符串数组用来存放数字串
		String maxstr = "";// 只存放最长的数字串
		int maxlength = 0;

		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
				// 次数的while循环和if中的判断语句一样，但是千万不可以少
				// while是为了将所有的数字串都加进tmp当中
				while (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
					tmp += str.charAt(i++);
				}
				if (tmp.length() > maxlength) {
					maxstr = tmp;
					maxlength = tmp.length();
				}
			}
			tmp = "";
		}
		System.out.println(maxstr.length() + "/" + maxstr);
	}

	public static void main(String[] args) {
		lengthOfLongestSubstring("abc360360xyz#123you");
	}
}
