package lai05;
/*
[Question]
    given a range, get values from a BST, put them in an array in ascending order
[Idea]
    array is ascending, so pre order
    if node.value <= min, no need to find in left
    if node.value >= max, no need to find in right
[Notice]
    why use a void return range
        we just need to manipulate the array, if we use list return getRange, for each layer in recursion, we need to
        save a list in the call stack
    how to create List
        use a linked list
[Complexity]
    O(N), we nearly check each node and justify
    O(height), the max depth of stack is the layer of tree
*/


import java.util.LinkedList;
import java.util.List;

public class Code09_GetKeysInBinarySearchTreeInGivenRange {

    public static List<Integer> getRange(TreeNode root, int min, int max) {
        List<Integer> res = new LinkedList<>();
        range(root, min, max, res);
        return res;
    }

    public static void range(TreeNode root, int min, int max, List<Integer> res) {
        if (root == null) {
            return;
        }
        if (root.key > min) {
            range(root.left, min, max, res);
        }
        if (root.key >=min && root.key <= max) {
            res.add(root.key);
        }
        if (root.key < max) {
            range(root.right, min, max, res);
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

        int min = 2;
        int max = 3;
        List<Integer> res = getRange(a, min, max);

        // 23
        for (int item : res) {
            System.out.println(item);
        }

    }
}
