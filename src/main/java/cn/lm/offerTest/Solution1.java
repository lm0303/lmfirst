package cn.lm.offerTest;
/**
 * 最长公共子数组
* Title: Solution1  
* Description:   
* @author 李猛  
* @date 2019年11月23日
 */
public class Solution1 {
	public static int getCommonArr(int[] A,int[] B){
		int lenA = A.length;
		int lenB = B.length;
		int res = 0;
		int[][] dp = new int[lenA+1][lenB];
		for (int i = 1; i < lenA; i++) {
			for (int j = 1; j < lenB; j++) {
				if (A[i-1] == B[j-1]) {
					dp[i][j] = dp[i-1][j-1] +1;
				}else {
					dp[i][j] = 0;
				}
				if (res < dp[i][j]) {
					res = dp[i][j];
				}
			}
		}
		return res;
	}
}
