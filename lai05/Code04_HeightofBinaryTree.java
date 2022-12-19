package lai05;
/*
[Question]
    get the height of a binary tree.
[Idea]
    max height of left subtree or right subtree + 1
*/

public class Code04_HeightofBinaryTree {

    public static class TreeNode {
        public int key;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int key) {
            this.key = key;
        }
    }

    public static int findHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return (Math.max(findHeight(root.left), findHeight(root.right))) + 1;
    }

    public static void main(String[] args) {

        // 03
        System.out.println(findHeight(null));
        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(3);
        a.left = b;
        b.left = c;
        System.out.println(findHeight(a));
    }
}
