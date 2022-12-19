package class10;

import java.util.Stack;

public class Code03_UnRecursiveTraversalBT {
    /*
    要求：用一个栈实现非递归的二叉树前序、中序、后序遍历
    思路：前序思路为弹栈顶，先压右后压左；中序思路为遍历压入做部分节点，不能压入则弹出并进入右节点；后序思路为先反向实现前序即中右左，再反向弹出即左右中
    */

    public static class Node{
        public int value;
        public Node left;
        public Node right;
        public Node(int data) {
            this.value = data;
        }
    }

    public static void pre(Node head) {
        Stack<Node> stack = new Stack<Node>();
        stack.add(head);
        while(!stack.isEmpty()){
            Node n = stack.pop();
            System.out.println(n.value);
            if (n.right != null) {
                stack.push(n.right);
            }
            if (n.left != null) {
                stack.push(n.left);
            }
        }
    }

    public static void in(Node head) {
        Stack<Node> stack = new Stack<Node>();
        Node cur = head;

        while (!stack.isEmpty() || cur!=null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                System.out.println(cur.value);
                cur = cur.right;
            }
        }
    }

    public static void pos(Node head) {
        Stack<Node> stack1 = new Stack<Node>();
        Stack<Node> stack2 = new Stack<Node>();
        stack1.add(head);
        while(!stack1.isEmpty()){
            Node n = stack1.pop();
            stack2.push(n);
            if (n.left != null) {
                stack1.push(n.left);
            }
            if (n.right != null) {
                stack1.push(n.right);
            }
        }
        while(!stack2.isEmpty()){
            System.out.println(stack2.pop().value);
        }
    }


    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        pre(head);
        System.out.println("========");
        in(head);
        System.out.println("========");
        pos(head);

    }

}
