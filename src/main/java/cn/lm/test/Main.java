package cn.lm.test;

import java.util.Arrays;
import java.util.Scanner;

public class Main {


/*请完成下面这个函数，实现题目要求的功能
当然，你也可以不按照下面这个模板来作答，完全按照自己的想法来 ^-^ 
******************************开始写代码******************************/
    static int solution(int[] arr) {
    	int num = 0;
    	int temp;
    	for(int i=0;i<arr.length;i++)
    	{
    		for(int j=i+1;j<arr.length;j++)
    		{
    			if(arr[i]>arr[j])
    			{
    				temp=arr[i];
    				arr[i]=arr[j];
    				arr[j]=temp;
    				num++;
    			}
    		}
    	}
    	return num;

    }
/******************************结束写代码******************************/


    public static void main(String[] args){
    	/*int res;
        Scanner in = new Scanner(System.in);
            
        int _arr_size = 0;
        _arr_size = Integer.parseInt(in.nextLine().trim());
        int[] _arr = new int[_arr_size];
        int _arr_item;
        for(int _arr_i = 0; _arr_i < _arr_size; _arr_i++) {
            _arr_item = Integer.parseInt(in.nextLine().trim());
            _arr[_arr_i] = _arr_item;
        }
        //int[] arr = {4,2,7,6};
        res = solution(_arr);
        res = solution(_arr);
        System.out.println(String.valueOf(res));   */ 
    	Scanner sc = new Scanner(System.in);
        int len = sc.nextInt();
        int[] nums = new int[len];
        int[] numssort = new int[len];
        for(int i = 0;i<len;i++){
             nums[i]=sc.nextInt();
        }
        numssort=nums.clone();
        Arrays.sort(numssort);
        int count=0;
        for(int j = 0;j<len;j++){
             if(nums[j]!=numssort[j])
             count++;
        }
        System.out.println(count/2);
        }

}
