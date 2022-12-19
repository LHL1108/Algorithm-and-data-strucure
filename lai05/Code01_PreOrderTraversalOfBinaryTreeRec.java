package lai05;
/*
[Question]
	pre order traverse a binary tree with recursion method
[Idea]
	put the operation in the head of recursion methods
[Complexity]
    Time: O(N), because we traverse all the nodes
    Spca: O(H), because the max depth of call stack is H
[Notice]
    use help function to solve the problems that have a different output data type
    the difference between List and Arraylist, how to initialize
*/

import java.util.ArrayList;
import java.util.List;

public class Code01_PreOrderTraversalOfBinaryTreeRec {
    public static class TreeNode {
        public int key;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int key) {
            this.key = key;
        }
    }

    public static List<Integer> preOrder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        myPreOrder(root, res);
        return res;
    }

    public static void myPreOrder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.key);
        myPreOrder(root.left, res);
        myPreOrder(root.right, res);
    }


    public static void main(String[] args) {
        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(3);
        a.left = b;
        a.right = c;

        List<Integer> res = preOrder(a);
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i));
        }
    }
}
