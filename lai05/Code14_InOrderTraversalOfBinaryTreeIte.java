package lai05;

/*
[Question]
    in order traverse a tree with iterative method
[Idea]
    use a stack, put the left most node, then visit its father or second left most node, then iterate its right subtree
[Notice]
    while loop condition, stack is not empty is not enough, it just mean no left node, doesn't mean no node
        eg. root tree without left subtree but have a right subtree,
    we couldn't get back from node to its parent, so we use stack.pollFirst to visit the middle
[Complexity]
    Time:  O(N), because it is a traverse algorithm
    Space: O(H), because it is same with recursion, we just simulate it
*/


import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Code14_InOrderTraversalOfBinaryTreeIte {

    public static List<Integer> inOrder(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        TreeNode cur = root;
        Deque<TreeNode> stack = new LinkedList<>();
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.offerFirst(cur);
                cur = cur.left;
            } else {
                cur = stack.pollFirst();
                res.add(cur.key);
                cur = cur.right;
            }
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

        // 123
        List<Integer> res = inOrder(a);
        for(int item : res) {
            System.out.println(item);
        }
    }



}
