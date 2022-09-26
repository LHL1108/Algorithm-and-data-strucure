package class03;

public class Code01_ReverseList {

    // 创建Node类
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            value = data;
        }
    }



    public static Node reverseLinkedList(Node head) {
        Node pre = null;
        Node aft = null;
        while (head != null) {
            // 修改指向前先记录好下个节点
            aft = head.next;
            head.next = pre;

            // 更改索引位置，注意不是next，而是替换，因为前向链表的指向已反转，所以不能用next
            pre = head;
            head = aft;
        }
        return pre;
    }

    public static DoubleNode reverseDoubleLinkedList(DoubleNode head) {
        DoubleNode pre = null;
        DoubleNode aft = null;
        while (head != null) {
            aft = head.next;
            head.next = pre;
            head.last = aft;

            pre = head;
            head = aft;
        }
        return pre;
    }

    public static class DoubleNode {
        public int value;
        public DoubleNode last;
        public DoubleNode next;

        public DoubleNode(int data) {
            value = data;
        }
    }
    public static void main(String[] args) {
/*
    反转链表
    1 -> 2 -> 3 -> null
    3 -> 2 -> 1 -> null
*/
/*
        // 测试1
        Node a = new Node(1);
        Node b = new Node(2);
        Node c = new Node(3);
        a.next = b;
        b.next = c;
        System.out.println("反转前： " + a.value + " " + a.next.value + " " + a.next.next.value);

        Node head = reverseLinkedList(a);
        System.out.println("反转后： " + head.value + " " + head.next.value+ " " + head.next.next.value);
*/
        // 测试2
        DoubleNode a = new DoubleNode(1);
        DoubleNode b = new DoubleNode(2);
        DoubleNode c = new DoubleNode(3);
        a.next = b;
        b.next = c;

        System.out.println("反转前： " + a.value + " " + a.next.value + " " + a.next.next.value);
        DoubleNode head = reverseDoubleLinkedList(a);
        System.out.println("反转后： " + head.value + " " + head.next.value+ " " + head.next.next.value);
    }


}




