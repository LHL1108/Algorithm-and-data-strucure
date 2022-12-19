package class11;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// 本题测试链接：https://leetcode.com/problems/encode-n-ary-tree-to-binary-tree
public class Code03_EncodeNaryTreeToBinaryTree {

    // 提交时不要提交这个类
    public static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };

    // 提交时不要提交这个类
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 只提交这个类即可
    class Codec {
        // Encodes an n-ary tree to a binary tree.
        public TreeNode encode(Node root) {
            if(root == null) {
                return null;
            }
            TreeNode head = new TreeNode(root.val);
            head.left = en(root.children);
            return head;
        }

        public static TreeNode en(List<Node> children) {
            TreeNode head = null;
            TreeNode cur = null;
            for(Node child : children) {
                if (head == null) {
                    head = new TreeNode(child.val);
                    cur = head;
                } else {
                    cur.right = new TreeNode(child.val);
                }
                cur = cur.right;
                cur.left = en(child.children);
            }
            return head;
        }

        public Node decode(TreeNode root) {
            if (root == null) {
                return null;
            }
            return new Node(root.val, de(root.left));
        }

        public static List<Node> de(TreeNode root) {
            List<Node> children = new ArrayList<>();
            while(root != null) {
                Node n = new Node(root.val, de(root.left));
                children.add(n);
                root = root.right;
            }
            return children;

        }
    }



}
