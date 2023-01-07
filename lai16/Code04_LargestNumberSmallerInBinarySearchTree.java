package lai16;

import java.util.LinkedList;
import java.util.Queue;

public class Code04_LargestNumberSmallerInBinarySearchTree {

    public static int findLargestSmaller(TreeNode root, int target) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }
        if (root.left == null && root.right == null) {
            return root.key;
        }

        int res = Integer.MIN_VALUE;
        while (root != null) {
            if (root.key < target && res == Integer.MIN_VALUE) {
                res = root.key;
            }
            res = root.key < target && target - root.key < target - res ? root.key : res;
            if (root.key < target) {
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return res;
    }

    public static class TreeNode {
        int key;
        TreeNode left;
        TreeNode right;
        TreeNode(int key) { this.key = key; }
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
        String[] lst = {"5", "2", "8"};
        TreeNode root = levelTraverse(lst);
        System.out.println(root.right.key);
        int res = findLargestSmaller(root, 6);
        //5
        System.out.println(res);
    }
}
