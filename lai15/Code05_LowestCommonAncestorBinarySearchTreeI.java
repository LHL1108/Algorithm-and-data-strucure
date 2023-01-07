package lai15;
/*
[question]
    in a BST, find the lowest common ancestor, two elements garentee to be in the tree
[idea]
    go to the left: root > large
    go to the right root < small
[complexity]
    time: avg logn, worst n
    space: 1

*/

import lai06.Code02_GeKeysInBinaryTreeLayerByLayer;

public class Code05_LowestCommonAncestorBinarySearchTreeI {


    public static TreeNode lca(TreeNode root, int v1, int v2) {
        int small = v1 < v2 ? v1 : v2;
        int large = v1 > v2 ? v1 : v2;
        while (root != null) {
            if (root.key > large) {
                root = root.left;
            } else if (root.key < small) {
                root = root.right;
            } else {
                return root;
            }
        }
        return null;
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
        TreeNode res = lca(b, 1, 3);
        // 2
        System.out.println(res.key);
    }
}
