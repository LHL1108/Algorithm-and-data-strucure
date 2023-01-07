package lai15;
/*
[question]
    find a lca in a binary tree that each node has a parent pointer?
    two nodes are not garentee to exist in the tree
	return the lca node
[idea]
    find two path from each node to root
    move the longer one firstly to the same level with the shorter one
    move together until they meet
[complexity]
    time:  O(n), because it might not be a balanced tree
    space: O(1)
[notice]
    if it is not a balanced tree, time complexity is not O(log n)
    change the input order of n1, n2 in two situations
    check return
*/


public class Code06_LowestCommonAncestorII {


    public static TreeNode lowestCommonAncestor(TreeNode n1, TreeNode n2) {
        if (n1 == null || n2 == null) {
            return null;
        }

        int path1 = findPath(n1);
        int path2 = findPath(n2);
        int diff = Math.abs(path1 - path2);

        if (path1 > path2) {
            return findLCA(n1, n2, diff);
        } else {
            return findLCA(n2, n1, diff);
        }
    }

    public static int findPath(TreeNode n) {
        int path = 0;
        while (n != null) {
            path++;
            n = n.parent;
        }
        return path;
    }


    public static TreeNode findLCA(TreeNode longer, TreeNode shorter, int diff) {
        while (diff > 0) {
            diff--;
            longer = longer.parent;
        }

        while (longer != shorter) {
            if (longer == null || shorter == null) {
                return null;
            }
            longer = longer.parent;
            shorter = shorter.parent;
        }

        return longer;
    }

    public static class TreeNode {
        public int key;
        public TreeNode left;
        public TreeNode right;
        public TreeNode parent;
        public TreeNode(int key) {
            this.key = key;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode a = new TreeNode(2);
        TreeNode b = new TreeNode(3);

        root.left = a;
        root.right = b;
        a.parent = root;
        b.parent = root;

        TreeNode res = lowestCommonAncestor(a, b);
        // 1
        System.out.println(res.key);
    }






}
