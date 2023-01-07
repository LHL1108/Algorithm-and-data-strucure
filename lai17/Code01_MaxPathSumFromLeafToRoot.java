package lai17;
import java.util.LinkedList;
import java.util.Queue;
/*
[question]
    given a binary tree, return its max path from leaf to root
         10
       -2  7  return 17
[idea]
    recurse the tree and update max[0], get max path of its left and right, return larger one + root.key
    notice the one leaf situation
[complexity]
    time: O(n)
    space: O(height)
[notice]
    pay attention to the requirement of these kinds of questions
    it is a simple question, we don't need a max[], we can return directly
*/

public class Code01_MaxPathSumFromLeafToRoot {

    public static int maxPathSumLeafToRoot(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left != null && root.right != null) {
            return root.key + Math.max(helper(root.left), helper(root.right));
        }
        return root.left == null ? root.key + helper(root.right) : root.key + helper(root.left);
    }


    public static int helper(TreeNode root) {
        if (root == null) {
                return 0;
        }
        int l = helper(root.left);
        int r = helper(root.right);
        if (root.left != null && root.right != null) {
            return l > r ? root.key + l : root.key + r;
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

        String[] lst = {"-16","-2","0","7","-11","2","#","-2","-5","-5","-15","5","15"};
        TreeNode root = levelTraverse(lst);

        int maxPath = maxPathSumLeafToRoot(root);
        // 1
        System.out.println(maxPath);
    }
}
