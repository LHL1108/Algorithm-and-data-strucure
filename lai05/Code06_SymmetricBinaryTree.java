package lai05;
/*
[Question]
    judge a tree if is a symmetric binary tree
[Idea]
    1.a tree is symmetric means, for each node, their two subtrees are symmetric;
    2.two trees are symmetric means, 1.head nodes are equal, 2.left trees left part equals right trees right part, 3.left trees right part equals
    right trees left part
[Complexity]
    Time: O(N), for each node, it just judge one time
    Space: O(height), the depth of call stacks won't be above the height of the tree
[Notice]
    to judge a symmetric tree, is to judge tree node's two subtree, they are independent
*/

public class Code06_SymmetricBinaryTree {

    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        return Symmetric(root.left, root.right);
    }

    public static boolean Symmetric(TreeNode one, TreeNode two){
        if (one == null && two == null) {
            return true;
        } else if (one == null || two == null) {
            return false;
        } else {
            if (one.key != two.key) {
                return false;
            } else {
                return Symmetric(one.left, two.right) && Symmetric(one.right, two.left);
            }
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


    public static void main(String[] args) {

        TreeNode a = new TreeNode(3);
        TreeNode b = new TreeNode(4);
        TreeNode c = new TreeNode(4);
        TreeNode d = new TreeNode(5);
        TreeNode e = new TreeNode(5);
        a.left = b;
        a.right = c;
        b.left = d;
        c.right = e;
        // true
        System.out.println(isSymmetric(a));
    }
}
