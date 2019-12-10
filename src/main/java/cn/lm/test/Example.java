package cn.lm.test;

import java.util.Scanner;

public class Example {

	public static int function(int n){
		int num = 1;
		boolean b = false;  
		int x = 2;  
	    while(true){  
	        if(x==n){  
	            b=true;  
	            break;  
	        }if(x<n){  
	            x=2*x;  
	        }else{  
	            b=false;  
	            break;  
	        }  
	    }  
	    if (b) {
			return 1;
		}else {
			for (int i = 0; i <= n; i++) {
				if (2*i+1 == n ) {
					num++;
				}
			}
			return num;
		}
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		//System.out.println(function(s.nextInt()));
		System.out.println("abc".substring(1));
	}
}
