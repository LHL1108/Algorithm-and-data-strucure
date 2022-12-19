package class10;

public class Code02_RecursiveTraversalBT {
    /*
    要求：实现二叉树的先序、中序、后序遍历功能
    思路：递归实现，关键在于为空的退出机制和打印的位置
    一步一步来：
    先实现二叉树结构
    */

    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static void f(Node head) {
        if (head == null) {
            return;
        }
        // 1
        f(head.left);
        // 2
        f(head.right);
        //3
    }

    public static void pre(Node head) {
        if (head == null) {
            return;
        }
        System.out.println(head.value);
        pre(head.left);
        // 2
        pre(head.right);
        //3
    }

    public static void in(Node head) {
        if (head == null) {
            return;
        }
        // 1
        in(head.left);
        System.out.println(head.value);
        in(head.right);
        //3
    }

    public static void aft(Node head) {
        if (head == null) {
            return;
        }
        // 1
        aft(head.left);
        // 2
        aft(head.right);
        System.out.println(head.value);
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
        System.out.println("===");
        in(head);
        System.out.println("===");
        aft(head);
        System.out.println("===");
    }






}