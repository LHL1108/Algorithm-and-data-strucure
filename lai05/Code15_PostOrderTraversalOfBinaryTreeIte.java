package lai05;
/*
[Question]
    traverse a tree with iterative method
[Idea]
    go down
        add cur to stack
        go left first
        go right then
    go back from left
        no right
            finish this node
            go up
        has right
            go right
    go back from right
        finish
        go up
    update pre
[Notice]
    stack means next iterative item, so put root first, if need iterate some node next round, put it in stack
    5 situation, first, go down to left, go down to right, go up from left, go up from right
        first, go down to left, go down to right has same code ,so merge them, if it is a leaf node, finish, go up
        if go up from left, then go down to right, if no right, finish, go up
        if go up from right, finish, so go up
    stack.offer means iterative this node next time
    stack.poll means we don't need this node in traversal any mode, add it then poll it
    don't forget to update cur and pre at the beginning and the end
    don't forget give a return
    don't forget use .key to add value in res
    use == in comparison
[Complexity]
    Time:  O(N), it is a traversal algorithm
    Space: O(H), it is the same with recursion
*/

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Code15_PostOrderTraversalOfBinaryTreeIte {


    public static List<Integer> postOrder(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode pre = null;
        stack.offerFirst(root);

        while (!stack.isEmpty()) {
            TreeNode cur = stack.peek();
            // go down
            if (pre == null || pre.left == cur || pre.right == cur) {
                if (cur.left != null) {
                    stack.offerFirst(cur.left);
                } else if (cur.right != null) {
                    stack.offerFirst(cur.right);
                } else if (cur.left == null && cur.right == null){
                    res.add(cur.key);
                    stack.pollFirst();
                }
            } else if (cur.left == pre) {
                if (cur.right != null) {
                    stack.offerFirst(cur.right);
                } else {
                    res.add(cur.key);
                    stack.pollFirst();
                }
            } else if (cur.right == pre) {
                res.add(cur.key);
                stack.pollFirst();
            }
            pre = cur;
        }
        return res;
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

        // 132
        List<Integer> res = postOrder(a);
        for(int item : res) {
            System.out.println(item);
        }
    }
}
