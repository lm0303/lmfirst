package cn.lm.test;

public class Main5 {
	public class ListNode{
		int val;
		ListNode next = null;
		public ListNode(int val) {
			this.val = val;
		}
	}
	public class Solution{
		public ListNode Merge(ListNode list1,ListNode list2){
			if (list1 == null) {
				return list2;
			}else if (list2 == null) {
				return list1;
			}
			ListNode newHead = null;
			if (list1.val < list2.val) {
				newHead = list1;
				list1 = list1.next;
				newHead.next = Merge(list1, list2);
			}else {
				newHead = list2;
				list2 = list2.next;
				newHead.next = Merge(list1, list2);
			}
			return newHead;
		}
	}
}
