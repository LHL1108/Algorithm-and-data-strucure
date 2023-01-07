package lai15;
import java.util.ArrayList;
import java.util.List;

/*
[question]
    find out the LCA of 2 nodes in a K-nary tree
[idea]
    traverse each subtree, find LCA for each subtree,
    if it is null, skip
    if it is not null and we have found another not null one, return root directly
    otherwise, give the value as found, return it
[complexity]
    time: O(n), traverse in the tree
    space: O(h), recursion in tree
*/




public class Code12_LowestCommonAncestorV {

    public static KnaryTreeNode lowestCommonAncestor(KnaryTreeNode root, KnaryTreeNode a, KnaryTreeNode b){
        if (root == null || root == a || root == b) {
            return root;
        }
        KnaryTreeNode found = null;
        for (KnaryTreeNode child : root.children) {
            KnaryTreeNode node = lowestCommonAncestor(child, a, b);
            if (node == null) {
                continue;
            } else if (found == null) {
                found = node;
            } else {
                return root;
            }
        }
        return found;
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
        KnaryTreeNode res = lowestCommonAncestor(a, c, d);
        // 1
        System.out.println(res.key);
    }

}
