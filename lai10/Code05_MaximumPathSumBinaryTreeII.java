package lai10;
/*
[Question]
    find the max path in the binary tree, from any node to any node, and the start and the end can be same
[Idea]
    max path could go through the root and maybe not, if go through, we just find the left max and right max single path and add them all
    if not we just update the path by add left and right and node value in the process of recursion
[Notice]
    use a int[] to record result
    the difference between final goal and recursion goal
[Complexity]
    Time: O(n)
    Space: O(h)
*/


public class Code05_MaximumPathSumBinaryTreeII {

    public static int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] max = {Integer.MIN_VALUE};
        helper(root, max);
        return max[0];
    }

    public static int helper(TreeNode root, int[] max) {
        if (root == null) {
            return 0;
        }
        int left = helper(root.left, max);
        int right = helper(root.right, max);
        left = Math.max(left, 0);
        right = Math.max(right, 0);
        max[0] = Math.max(max[0], left + root.key + right);
        return root.key + Math.max(left, right);
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
        TreeNode n1 = new TreeNode(-86);
        TreeNode n2 = new TreeNode(-88);
        TreeNode n3 = new TreeNode(-13);
        n1.left = n2;
        n1.right = n3;
        //1
        System.out.println(maxPathSum(n1));
    }
}
