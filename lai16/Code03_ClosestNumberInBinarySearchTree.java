package lai16;
/*
[question]
    find the closest number to the target in a BST
[idea]
    search the target until we find it or couldn't find it(fall in null)
    in the process, update the result
[complexity]
    time: O(n)， search in a BST, worst case
    space: O(1)
[notice]
    consider the left or right is null in if judgement, or we could update first then move to avoid compare null
*/

public class Code03_ClosestNumberInBinarySearchTree {

    public static int closest(TreeNode root, int target) {
        if (root.key == target) {
            return target;
        }

        TreeNode cur = root;
        int res = cur.key;
        while (cur != null) {
            if (target == cur.key) {
                return target;
            } else {
                res = Math.abs(res - target) <= Math.abs(cur.key - target) ? res : cur.key;
                if (target < cur.key) {
                    cur = cur.left;
                } else {
                    cur = cur.right;
                }
            }
        }
        return res;
    }

    public static class TreeNode {
        int key;
        TreeNode left;
        TreeNode right;
        TreeNode(int key) { this.key = key; }
    }

    public static void main(String[] args) {
        TreeNode a = new TreeNode(5);
        TreeNode b = new TreeNode(1);
        TreeNode c = new TreeNode(10);
        TreeNode d = new TreeNode(8);
        a.left = b;
        a.right = c;
        c.left = d;
        // 8
        System.out.println(closest(a, 7));
    }
}
