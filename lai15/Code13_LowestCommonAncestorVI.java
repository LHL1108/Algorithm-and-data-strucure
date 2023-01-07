package lai15;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/*
[question]
    find the LCA of a list of nodes in a K-nary tree
[idea]
    the same idea with find two in K-nary
    first, transfer list to set
    second, if root is in set, return root
    otherwise, recurse its children
        if find 2 nodes not null, return root
        otherwise return the 1 node or null
[complexity]
    time: O(n), traverse all nodes
    space: O(h+k), recursion tree height and k set
[notice]
    in recursion, we recurse its child

*/


public class Code13_LowestCommonAncestorVI {

    public static KnaryTreeNode lowestCommonAncestor(KnaryTreeNode root, List<KnaryTreeNode> nodeList) {
        Set<KnaryTreeNode> set = new HashSet<>(nodeList);
        return helper(root, set);
    }

    public static KnaryTreeNode helper(KnaryTreeNode root, Set<KnaryTreeNode> set) {
        if (root == null || set.contains(root)) {
            return root;
        }
        KnaryTreeNode firstNode = null;
        for (KnaryTreeNode child : root.children) {
            KnaryTreeNode node = helper(child, set);
            if (node == null) {
                continue;
            } else if (firstNode == null) {
                firstNode = node;
            } else {
                return root;
            }
        }
        return firstNode;
    }

    public static class KnaryTreeNode {
        int key;
        List<KnaryTreeNode> children;
        public KnaryTreeNode(int key) {
            this.key = key;
            this.children = new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        KnaryTreeNode a = new KnaryTreeNode(1);
        KnaryTreeNode b = new KnaryTreeNode(2);
        KnaryTreeNode c = new KnaryTreeNode(3);
        KnaryTreeNode d = new KnaryTreeNode(4);
        a.children.add(b);
        a.children.add(c);
        a.children.add(d);
        List<KnaryTreeNode> nodeList = new ArrayList<>();
        nodeList.add(b);
        nodeList.add(c);
        nodeList.add(d);
        KnaryTreeNode res = lowestCommonAncestor(a, nodeList);
        // 1
        System.out.println(res.key);
    }
}
