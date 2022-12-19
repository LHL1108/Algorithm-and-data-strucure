package lai05;
import java.util.ArrayList;
import java.util.List;

/*
[Question]
    in order traverse a binary tree with recursion method
[Idea]
    put the operation in the middle of recursion methods
[Complexity]
    Time: O(N), because we traverse all the nodes
    Spca: O(H), because the max depth of call stack is H
*/


public class Code02_InOrderTraversalOfBinaryTreeRec {

    public static class TreeNode {
        public int key;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int key) {
            this.key = key;
        }
    }

    public static List<Integer> inOrder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        myInOrder(root, res);
        return res;
    }

    public static void myInOrder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }

        myInOrder(root.left, res);
        res.add(root.key);
        myInOrder(root.right, res);
    }



    public static void main(String[] args) {
        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(3);
        a.left = b;
        a.right = c;

        List<Integer> res = inOrder(a);

        // 213
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i));
        }
    }
}


