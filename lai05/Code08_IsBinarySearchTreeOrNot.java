package lai05;
/*
[Question]
    determine if a binary tree is a BST
[Idea]
    BST = left is BST, right is BST, root > left.max and root < right.min
    -> left and right is BST in certain range depends on root
[Process]
    main
        isBST(root, min, max)
    isBST
        isBST(left, min, root.key), isBST(right, root.key, max)
[Notice]
    the value couldn't be equal, so key <= min, key >=max
[Complexity]
    time: O(N), because we nearly visit each node and judge
    Space: O(height), because the max depth of call stack is height
*/

public class Code08_IsBinarySearchTreeOrNot {



    public static boolean isBST(TreeNode root) {
        return BST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static boolean BST(TreeNode root, int min, int max) {
        if (root == null) {
            return true;
        }
        if (root.key <= min || root.key >= max) {
            return false;
        }
        return BST(root.left, min, root.key) && BST(root.right, root.key, max);
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

        TreeNode a = new TreeNode(6);
        TreeNode b = new TreeNode(4);
        TreeNode c = new TreeNode(10);
        TreeNode d = new TreeNode(2);
        TreeNode e = new TreeNode(5);
        TreeNode f = new TreeNode(6);
        TreeNode g = new TreeNode(12);
        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        c.left = f;
        c.right = g;

        // true
        System.out.println(isBST(a));
    }
}
