package lai10;

/*
[Question]
    get each node's left nodes number
[Idea]
    node's left number is node's left total
    total is left + 1 + right
[Notice]
    goal can be a part of recursion, or be finished in the process of recursion, not must be the answer of recursion
[Complexity]
    Time: O(n)
    Space: O(height), recursion
*/

public class Code03_StoreNumberOfNodesInLeftSubtree {

    public static void numNodesLeft(TreeNodeLeft root) {
        getNum(root);
    }

    public static int getNum(TreeNodeLeft root) {
        if (root == null) {
            return 0;
        }
        int left = getNum(root.left);
        root.numNodesLeft = left;
        int right = getNum(root.right);
        return left + 1 + right;
    }


    public class TreeNodeLeft {
        public int key;
        public TreeNodeLeft left;
        public TreeNodeLeft right;
        public int numNodesLeft;
        public TreeNodeLeft(int key) {
            this.key = key;
        }
    }
}
