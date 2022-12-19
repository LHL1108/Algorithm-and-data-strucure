package lai05;
/*
[Question]
    Delete node in a BST, return the root after deletion
[Idea]
    main
        root.value == key
            delete
        root.value > key
            recurse left
        root.value < key
            recurse right
    delete
        no left and right
            return null
        no left
            return right
        no right
            return left
        has left and right
            right no left
                link left to right's left
                return right
            right has left
                extract right smallest
                take over toot
                return right smallest
    extract
        go to left most
        connect left most's right to its father's left
        return left most
[Complexity]
    Time: O(logN), worst case O(N), because each recursion, we reduce half of nodes, but it could be O(N) when all nodes on the same side
    Space:O(height), the max depth of recursion tree is the height of input tree
[Notice]
    after recursion, we linked the head to the root and then return the root, not return its result directly, figure it out when recursion
    in extractSmallest, use your pen and paper to simulate it, not just think or guess 
*/

public class Code12_DeleteInBinarySearchTree {

    public static TreeNode deleteTree(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        if (root.key == key) {
            return deleteRoot(root);
        } else if (root.key > key) {
            root.left = deleteTree(root.left, key);
            return root;
        } else {
            root.right = deleteTree(root.right, key);
            return root;
        }
    }

    public static TreeNode deleteRoot(TreeNode root) {
        if (root.left == null) {
            return root.right;
        } else if (root.right == null) {
            return root.left;
        } else {
            if (root.right.left == null) {
                root.right.left = root.left;
                return root.right;
            } else {
                TreeNode rightSmallest = extractSmallest(root.right);
                rightSmallest.left = root.left;
                rightSmallest.right = root.right;
                return rightSmallest;
            }
        }
    }

    public static TreeNode extractSmallest(TreeNode root) {
        TreeNode cur = root;
        TreeNode father = root;
        cur = cur.left;

        while (cur.left != null) {
            father = cur;
            cur = cur.left;
        }
        father.left = cur.right;
        return cur;
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

        TreeNode a1 = new TreeNode(5);
        TreeNode b1 = new TreeNode(2);
        TreeNode b2 = new TreeNode(10);
        TreeNode c1 = new TreeNode(1);
        TreeNode c2 = new TreeNode(3);
        TreeNode c3 = new TreeNode(8);
        TreeNode c4 = new TreeNode(12);

        a1.left = b1;
        a1.right = b2;
        b1.left = c1;
        b1.right = c2;
        b2.left = c3;
        b2.right = c4;
/*
         5
      2    10
    1  3  8  12
*/
        int key = 10;

        // 5
        System.out.println(deleteTree(a1, key).key);
        // 12
        System.out.println(deleteTree(a1, key).right.key);
        // 8
        System.out.println(deleteTree(a1, key).right.left.key);
    }
}
