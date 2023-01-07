package lai15;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

/*
[question]
    in a BST, given a double target value, and k, return k closest elements to the target in BST, return in ascending order
[idea]
    use in order traverse to get the ascending values from min to target, put them into a stack
    use reverse in order traverse to get the descending values from max to target, put them into a stack
    now two stack top value are both closest to the target, one is getting smaller, and one is getting larger
    not compare and pop stack to the result list, until it is full
[complexity]
    time: O(n), traverse and compare in stack
    space: O(n), put all elements into stacks
[notice]
    how to implement in-order traverse
    in-order in BST is ascending, reverse in-order in BST is descending
    stop when meet the root from min or max
    use a stack to get the closest element
    use a minheap to sort result
    k might be larger than the number of elements, so break in advance
    keep the function name same in in order and reverse in order
*/


public class Code10_ClosestNumberInBinarySearchTreeII {

    public static int[] closestKValues(TreeNode root, double target, int k) {
        if (root == null) {
            return null;
        }
        int[] res = new int[k];
        int cnt = 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        Deque<Integer> stackFromSmallToLarge = new LinkedList<>();
        Deque<Integer> stackFromLargeToSamll = new LinkedList<>();
        inorder(root, target, k, stackFromSmallToLarge);
        reverseInorder(root, target, k, stackFromLargeToSamll);
        for (int i = 0; i < res.length; i++) {
            if (stackFromSmallToLarge.isEmpty() && stackFromLargeToSamll.isEmpty()) {
                break;
            } else if (stackFromSmallToLarge.isEmpty()) {
                minHeap.add(stackFromLargeToSamll.pop());
            } else if (stackFromLargeToSamll.isEmpty()) {
                minHeap.add(stackFromSmallToLarge.pop());
            } else if (Math.abs(target - stackFromSmallToLarge.peek()) <= Math.abs(target - stackFromLargeToSamll.peek())) {
                minHeap.add(stackFromSmallToLarge.pop());
            } else {
                minHeap.add(stackFromLargeToSamll.pop());
            }
        }
        while (!minHeap.isEmpty()) {
            res[cnt++] = minHeap.poll();
        }

        return Arrays.copyOfRange(res, 0, cnt);
    }

    public static void inorder(TreeNode root, double target, int k, Deque stackFromSmallToLarge) {
        if (root == null) {
            return;
        }
        inorder(root.left, target, k, stackFromSmallToLarge);
        if (root.key > target) {
            return;
        }
        stackFromSmallToLarge.push(root.key);
        inorder(root.right, target, k, stackFromSmallToLarge);
    }

    public static void reverseInorder(TreeNode root, double target, int k, Deque stackFromLargeToSamll) {
        if (root == null) {
            return;
        }
        reverseInorder(root.right, target, k, stackFromLargeToSamll);
        if (root.key <= target) {
            return;
        }
        stackFromLargeToSamll.push(root.key);
        reverseInorder(root.left, target, k, stackFromLargeToSamll);
    }

    public static class TreeNode {
        public int key;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int key) {
            this.key = key;
        }
    }
/*

    5
   2  7
  1 4 6
 target = 2, k = 4, return [1, 2, 4, 5]  not[1, 2, 4, 0]
*/
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode a = new TreeNode(2);
        TreeNode b = new TreeNode(7);
        TreeNode c = new TreeNode(1);
        TreeNode d = new TreeNode(4);
        TreeNode e = new TreeNode(6);
        root.left = a;
        root.right = b;
        a.left = c;
        a.right = d;
        b.left = e;
        int[] res = closestKValues(root, 2.0, 2);
        // [1, 2]
        System.out.println(Arrays.toString(res));
    }


}
