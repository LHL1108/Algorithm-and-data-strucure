package lai10;
/*
[Question]
    Reverse a tree with, make the left bottom become the top
[Idea]
    recursion, leave root and root.right alone
[Notice]
    Don't forget to change root's point
    use root.left to link, not newHead
[Complexity]
    Time:  O(n), because no right leaf, h is n
    Space: O(n), because no right leaf, h is n
*/

public class Code06_ReverseBinaryTreeUpsideDown {

    public TreeNode reverse(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return root;
        }

        TreeNode newHead = reverse(root.left);
        root.left.left = root;
        root.left.right = root.right;
        root.left = null;
        root.right = null;
        return newHead;
    }

    public static class TreeNode {
        public int key;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int key) {
            this.key = key;
        }
    }

}
