package lai05;
/*
[Question]
    check if the binary tree is balanced
[Idea]
    left subtree is balanced and right subtree is balance and the difference of left and right height is not above 1
[Complexity]
    Time: O(NlogN) check each node, but the height is getting smaller, so N(each node) * logN（average complexity to get height）
    Space: O(height), the depth of call stack won't be larger than height
[Notice]
    the use of Math.abs()
*/


public class Code05_CheckIfBinaryTreeIsBalanced {

    public static boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isBalanced(root.left) && isBalanced(root.right) && Math.abs(findHeight(root.left) - findHeight(root.right)) <= 1;
    }

    public static int findHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(findHeight(root.left), findHeight(root.right)) + 1;
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

        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(3);
        a.left = b;
        b.left = c;
        // false
        System.out.println(isBalanced(a));
    }
}
