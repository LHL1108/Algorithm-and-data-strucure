package lai06;
/*
[Question]
    check if a tree is a completed Binary Tree, return true or false
[Idea]
    we can traverse a tree and use a seenNull flag to record null, if see null, turn it to true, and
    after that we couldn't see null again
[Process]
    traverse a tree
        left is null
            turn seenNull flag to true
        left not null
            if seen null already
                return false
            if not
                put it in
        right same
[Complexity]
    Time:  O(N), we layer traverse a tree
    Space: O(N), we use a queue to layer traverse a tree
*/

import java.util.LinkedList;
import java.util.Queue;

public class Code03_CheckIfBinaryTreeIsCompleted {

    public static boolean isCompleted(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        boolean seenNull = false;

        while (!q.isEmpty()) {
            TreeNode cur = q.poll();

            if (cur.left == null) {
                seenNull = true;
            } else {
                if (seenNull) {
                    return false;
                } else {
                    q.offer(cur.left);
                }
            }
            if (cur.right == null) {
                seenNull = true;
            } else {
                if (seenNull) {
                    return false;
                } else {
                    q.offer(cur.right);
                }
            }
        }
        return true;
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
        //a.left = b;
        a.right = c;

        // false
        System.out.println(isCompleted(a));
    }
}
