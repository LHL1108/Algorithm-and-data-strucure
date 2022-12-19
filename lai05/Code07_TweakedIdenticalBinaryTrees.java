package lai05;
/*
[Question]
    to judge if two trees are tweaked trees, means swap any children of nodes
[Idea]
    head
        must be the same
    children
        left == left, right == right or left == right, right == left
Complexity:
    Time: O(N^2), because there are 4 judgement situation in recursion tree, height is log2_N. 4^log2_N = N^2
    Space: O(height), the max depth of call stack is the height of the tree
[Notice]
    when compare two nodes value, use .key
    how situation changes, 2 null, 1 null, 0 null(not equal, equal)
    we should consider left and right at the same time in similar or symmetric situation
*/

public class Code07_TweakedIdenticalBinaryTrees {


    public static boolean isTweakedIdentical(TreeNode one, TreeNode two) {
        if (one == null && two == null) {
            return true;
        } else {
            if (one == null || two == null) {
                return false;
            } else {
                if (one.key != two.key) {
                    return false;
                } else {
                    return isTweakedIdentical(one.left, two.left) && isTweakedIdentical(one.right, two.right) ||
                            isTweakedIdentical(one.left, two.right) && isTweakedIdentical(one.right, two.left);
                }
            }
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

        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(3);
        TreeNode c = new TreeNode(2);
        a.left = b;
        a.right = c;

        TreeNode A = new TreeNode(1);
        TreeNode B = new TreeNode(2);
        TreeNode C = new TreeNode(3);
        A.left = B;
        A.right = C;
        // true
        System.out.println(isTweakedIdentical(a, A));
    }
}
