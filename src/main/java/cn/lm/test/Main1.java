package cn.lm.test;

import java.util.Scanner;

public class Main1 {
	//求最大子数组的和
	
		//暴力求解
		public static int getMaxChildA(int[] a){
		
			int i,j,low=0,high=0;
			int addMax = 0;
			int temp = 0;
			for(i=0;i<a.length;i++){
				for(j=i;j<a.length;j++){
					temp += a[j];
					if(addMax < temp){
						addMax = temp;
						low = i;
						high = j;
					}
				}
				temp = 0;
			}
			
			return addMax;
		} 
	 
		//时间复杂度为o(n)
		/**
		 * 思路：设前n项和为sum,
		 * 若前i-1项和大于零，加上第i项后小于0了，则最小子序列从a[i+1]项开始，继续向后加
		 * 若前i-1项和大于零,加上第i项后大于零，则继续向后加	
		 * @param arr
		 * @return
		 */
		 static int findMaxArr3(int[] arr)
		    {
		        int max = arr[0];// 最大值
		        int sum = 0;// 求和
		        int startIndex = 0;
		        int endIndex = 0;
		        for (int i = 0; i < arr.length; i++)
		        {
		            if (sum >= 0)
		            {
		                sum += arr[i];
		            }
		            else
		            {
		                sum = arr[i];//前i-1项和小于0，最大子序列从第i项开始
		                startIndex = i;// 最大子数组开始值
		            }
		            if (sum > max)
		            {
		                max = sum;
		                endIndex = i;// 最大子数组结束值
		            }
		        }
		        System.out.println(startIndex+":"+endIndex);
		        return max;
		    }
	 
		
		
		public static void main(String[] args) {
			// TODO Auto-generated method stub
	 
			int[] a = {-1,2,-3,6};
			System.out.println(findMaxArr3(a));
			
		}

}
