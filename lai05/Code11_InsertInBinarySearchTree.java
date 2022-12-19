package lai05;
/*
[Question]
    insert a target value in a binary tree, if the value has existed, no need to do anything,return the root after insertion
[Idea]
    compare the root value with the target, insert in left or right subtree, insert when there is a null
[Complexity]
    Time: O(N), recurse each tree node, no repeat process
    Space: O(height), the max call stack depth is height of tree
*/

public class Code11_InsertInBinarySearchTree {


    public static TreeNode insert(TreeNode root, int key) {
        if (root == null) {
            return new TreeNode(key);
        }

        helper(root, key);
        return root;
    }

    public static void helper(TreeNode root, int key) {
        if (root.key == key) {
            return;
        } else if (root.key > key) {
            if (root.left == null) {
                root.left = new TreeNode(key);
            } else {
                helper(root.left, key);
            }
        } else {
            if (root.right == null) {
                root.right = new TreeNode(key);
            } else {
                helper(root.right, key);
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

        TreeNode a = new TreeNode(2);
        TreeNode b = new TreeNode(1);
        TreeNode c = new TreeNode(3);
        a.left = b;
        a.right = c;

        int key = 4;

        //4
        insert(a, key);
        System.out.println(a.right.right.key);
    }
}
