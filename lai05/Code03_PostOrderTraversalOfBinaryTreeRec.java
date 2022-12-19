package lai05;

/*
[Question]
    post order traverse a binary tree with recursion method
[Idea]
    put the operation in the last of recursion methods
[Complexity]
    Time: O(N), because we traverse all the nodes
    Spca: O(H), because the max depth of call stack is H
*/


import java.util.ArrayList;
import java.util.List;

public class Code03_PostOrderTraversalOfBinaryTreeRec {
    public static class TreeNode {
        public int key;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int key) {
            this.key = key;
        }
    }

    public static List<Integer> postOrder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        myPostOrder(root, res);
        return res;
    }

    public static void myPostOrder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        myPostOrder(root.left, res);
        myPostOrder(root.right, res);
        res.add(root.key);
    }

    public static void main(String[] args) {
        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(3);
        a.left = b;
        a.right = c;

        List<Integer> res = postOrder(a);

        // 231
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i));
        }
    }
}
