package cn.lm.test;

import java.util.LinkedList;
import java.util.List;

public class TreeNode {
	private int val;
	private TreeNode left;
	private TreeNode right;
	static int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	static List<TreeNode> nodeList = new LinkedList();

	public TreeNode(int x) {
		// TODO Auto-generated constructor stub
		this.val = x;
	}

	public TreeNode() {
		// TODO Auto-generated constructor stub
	}

	public void createTree() {
		for (int index = 0; index < array.length; index++) {
			nodeList.add(new TreeNode(array[index]));
		}
		// 最后一个父节点在数组中的索引
		int lastParentIndex = array.length / 2 - 1;
		for (int parentInex = 0; parentInex < lastParentIndex; parentInex++) {
			nodeList.get(parentInex).left = nodeList.get(parentInex * 2 + 1);
			nodeList.get(parentInex).right = nodeList.get(parentInex * 2 + 2);
		}
		// 最后一个父节点:因为最后一个父节点可能没有右孩子，所以单独拿出来处理
		// 左孩子
		nodeList.get(lastParentIndex).left = nodeList.get(lastParentIndex * 2 + 1);
		// 右孩子
		if (array.length % 2 == 1) {
			nodeList.get(lastParentIndex).right = nodeList.get(lastParentIndex * 2 + 2);
		}
	}

	// 递归方式
	public TreeNode invertTree(TreeNode root) {
		if (root == null) {
			return null;
		}

		// 递归反转左右子树
		TreeNode temp = root.left;
		root.left = invertTree(root.right);
		root.right = invertTree(temp);

		return root;

	}

	// 层次遍历
	public void levelTraverse(TreeNode root) {
		if (root == null)
			return;

		LinkedList<TreeNode> list = new LinkedList<>();
		list.add(root);
		while (list.size() != 0) {
			TreeNode node = list.removeFirst();
			System.out.print(node.val + " ");
			if (node.left != null) {
				list.add(node.left);
			}
			if (node.right != null) {
				list.add(node.right);
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		TreeNode treeNode = new TreeNode();
		treeNode.createTree();
		treeNode.levelTraverse(nodeList.get(0));
		TreeNode newRoot = treeNode.invertTree(nodeList.get(0));
		treeNode.levelTraverse(newRoot);
	}
}
