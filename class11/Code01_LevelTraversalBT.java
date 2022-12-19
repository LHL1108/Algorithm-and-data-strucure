package class11;


import java.util.LinkedList;
import java.util.Queue;

public class Code01_LevelTraversalBT {
    /*
    要求：使用队列来实现对二叉树的按层遍历
    思路:按层遍历包含两个含义，一个是依次遍历每一层，一个是每一层依次从左到右
        所以在因为上一层是从左到右打印的，所以在打印的过程中，同步将压入左右子，确保下一层遍历的正确进行

        一步一步来，先定义节点
        定义函数
        定义队列
        非空判断
        塞入头
        创建循环函数框架
        弹出元素并打印头节点
        压入左右元素
    */

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static void level(Node head) {
        Queue<Node> queue = new LinkedList<>();
        if (head == null) {
            return;
        }
        queue.add(head);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur.value);
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
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

        level(head);
        System.out.println("========");
    }

}


/*
期望输出：
1
2
3
4
5
6
7
*/