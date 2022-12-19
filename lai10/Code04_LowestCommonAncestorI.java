package lai10;
/*
[Question]
    find the lowest common node of one and two
[Idea]
    root is null
    root is one of them
    root is in the subtree
        different subtree
            return root
        same subtree
            return that subtree
[Complexity]
    Time: O(n)
    Space: O(h)
*/


public class Code04_LowestCommonAncestorI {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode one, TreeNode two) {
        if (root == null) {
            return null;
        }
        if (root == one || root == two) {
            return root;
        }
        TreeNode a = lowestCommonAncestor(root.left, one, two);
        TreeNode b = lowestCommonAncestor(root.right, one, two);
        if (a != null && b != null) {
            return root;
        } else if (a != null) {
            return a;
        } else {
            return b;
        }
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