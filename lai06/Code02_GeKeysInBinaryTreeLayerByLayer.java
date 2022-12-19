package lai06;
/*
[Question]
    given a tree, output layer traversal in a 2D list
[Idea]
    use a queue, put node's children when pop it, use a cnt to count the number of next layer
[Notice]
    when we judge if node has left and right child, we need to use two independent if
    queue only has offer and poll
    queue is implemented by linked list
    if we need to add something to the data structure and then return, initialize and return directly at cleaning check
[Complexity]
    Time:  O(N), because layer traverse is a traversal algorithm
    Space: O(N), generate a N size list
*/


import java.util.*;

public class Code02_GeKeysInBinaryTreeLayerByLayer {


    public static List<List<Integer>> layerByLayer(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) {
            return  res;
        }

        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> layer = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                layer.add(cur.key);
                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
            }
            res.add(layer);
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

        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(3);
        a.left = b;
        a.right = c;

        // [[1], [2, 3]]
        List<List<Integer>> res = layerByLayer(a);
        for (List<Integer> l : res) {
            for (int item : l) {
                System.out.println(item);
            }
            System.out.println("--- ");
        }
    }


}
