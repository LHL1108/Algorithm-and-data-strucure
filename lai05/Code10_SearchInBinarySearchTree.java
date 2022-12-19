package lai05;
/*
[Question]
    find the target in BST, return the node or null
[Idea]
    compare the root and then search in left or right
[Complexity]
    Time: O(N), because we nearly search each node and judge 1 time
    Space:O(height), the max depth of call stack is the height of tree
[Notice]
    use .key to compare root value
*/

public class Code10_SearchInBinarySearchTree {

    public static TreeNode search(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.key == key) {
            return root;
        } else if (root.key > key) {
            return search(root.left, key);
        } else {
            return search(root.right, key);
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

        int key = 2;
        TreeNode res = search(a, key);

        // 2
        System.out.println(res.key);
    }
}
