package lai05;
/*
[Question]
    pre order traverse a tree with iterative method
[Idea]
    use a stack to simulate recursion, first pop root, then offer its right and left node
[Notice]
    Node and value when compare or transform in different data type
    first offer right because we need it come out last
[Complexity]
    O(N), because it is a traversal algorithm
    O(H), because we are just simulate the recursion, the call stack max depth is the height of tree
*/

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Code13_PreOrderTraversalOfBinaryTreeIte {

    public static List<Integer> preOrder(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.offerFirst(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pollFirst();
            res.add(cur.key);
            if (cur.right != null) {
                stack.offerFirst(cur.right);
            }
            if (cur.left != null) {
                stack.offerFirst(cur.left);
            }
        }
        return res;
    }


    public static class TreeNode {
        public int key;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int key) {
            this.key = key;
        }
    }

    public static void main(String[] args) {

        TreeNode a = new TreeNode(2);
        TreeNode b = new TreeNode(1);
        TreeNode c = new TreeNode(3);
        a.left = b;
        a.right = c;

        //213
        List<Integer> res = preOrder(a);
        for(int item : res) {
            System.out.println(item);
        }
    }







}
