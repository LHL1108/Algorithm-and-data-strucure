package lai15;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/*
[question]
    given a root and a list of K nodes, find the LCA in binary tree (K > 2, all nodes are garentee to be in the tree)
[idea]
    deduplicate the nodes with set
    if the root is null or is in the set, return root
    other wise find LCA in root.left and root.right
        if they are both not null, return root
        otherwise, return the not null one
        else return null
[complexity]
    time: O(n), we traverse each node
    space: O(h + k), set space K + recursion space h
[notice]
    set.contains(), figure out the data type
*/

public class Code11_LowestCommonAncestorIV {

    public static TreeNode lowestCommonAncestor(TreeNode root, List<TreeNode> nodes) {
        Set<TreeNode> set = new HashSet<>(nodes);
        return helper(root, set);
    }

    public static TreeNode helper(TreeNode root, Set<TreeNode> set) {
        if (root == null || set.contains(root)) {
            return root;
        }

        TreeNode l = helper(root.left, set);
        TreeNode r = helper(root.right, set);
        if (l != null && r != null) {
            return root;
        }
        return l != null ? l : r;
    }

    public static class TreeNode {
        int key;
        TreeNode left;
        TreeNode right;
        TreeNode(int key) { this.key = key; }
    }

    public static void main(String[] args) {
        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(3);
        a.left = b;
        a.right = c;

        List<TreeNode> nodes = new ArrayList<>();
        nodes.add(b);
        nodes.add(c);
        TreeNode res = lowestCommonAncestor(a, nodes);
        // 1
        System.out.println(res.key);
    }

}
