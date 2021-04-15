package cn.lm.offerTest;

import java.util.ArrayList;
import java.util.Stack;
/**
 * 逆序输出链表
* Title: Solution  
* Description:   
* @author 李猛  
* @date 2019年11月23日
 */
public class Solution {
	public ArrayList<Integer> printLink(TreeNode treeNode){
		Stack<Integer> stack = new Stack<>();
		ArrayList<Integer> list = new ArrayList<>();
		while (treeNode != null) {
			stack.push(treeNode.val);
			treeNode = treeNode.next;
		}
		while (!stack.isEmpty()) {
			list.add(stack.pop());
		}
		return list;
	}
}
