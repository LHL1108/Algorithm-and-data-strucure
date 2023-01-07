package lai05;

import lai16.Code04_LargestNumberSmallerInBinarySearchTree;

import java.util.LinkedList;
import java.util.Queue;

public class Code16_MaximumPathSumBinaryTreeI {

    public static int maxPathSum(TreeNode root) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }
        int[] max = new int[]{Integer.MIN_VALUE};
        helper(root, max);
        return max[0];
    }

    public static int helper(TreeNode root, int[] max) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return root.key;
        }
        int l = helper(root.left, max);
        int r = helper(root.right, max);
        if (root.left != null && root.right != null) {
            max[0] = Math.max(max[0], l + root.key + r);
            return root.key + Math.max(l, r);
        }
        return root.left == null ? root.key + r : root.key + l;
    }



    // for test
    public static class TreeNode {
        public int key;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int key) {
            this.key = key;
        }
    }

    public static TreeNode levelTraverse(String[] lst) {
        if (lst == null || lst.length == 0) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(lst[0]));
        int i = 1;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (i < lst.length && lst[i] != "#") {
                node.left = new TreeNode(Integer.parseInt(lst[i]));
                q.add(node.left);
            }
            if (i + 1 < lst.length && lst[i+1] != "#") {
                node.right = new TreeNode(Integer.parseInt(lst[i + 1]));
                q.add(node.right);
            }
            i += 2;
        }
        return root;
    }

    public static void main(String[] args) {
        String[] lst = {"-15", "2", "11", "#", "#", "6", "14"};
        TreeNode root = levelTraverse(lst);

        int maxPath = maxPathSum(root);
        // 31
        System.out.println(maxPath);
    }
}
