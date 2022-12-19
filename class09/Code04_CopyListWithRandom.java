package class09;

import java.util.HashMap;

public class Code04_CopyListWithRandom {
    /*
    要求：有一个包含random数组的链表，请对其进行复制，返回复制链表的头节点
    思路：
        1.容器法： 构建哈希表，第一遍遍历保存所有节点，将其与原节点关联，第二遍通过原链表的关系，根据哈希表找到新链表节点并进行连接
        2.链表法： 将每一个节点的复制节点放在该节点后，通过原链表节点的关系及位置确定新链表的random关系和位置，最后重连整个链表
    易错点：
        两次遍历的时候需要将重新运行cur=head将其重置至链表头部
    */

    public static class Node {
        public int value;
        public Node next;
        public Node rand;

        public Node(int data){
            this.value = data;
        }
    }

    public static Node copyListWithRand1(Node head) {

        HashMap<Node, Node> hash = new HashMap<Node, Node>();
        Node cur = head;
        while (cur != null) {
            hash.put(cur, new Node(cur.value));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            hash.get(cur).next = hash.get(cur.next);
            hash.get(cur).rand = hash.get(cur.rand);
            cur = cur.next;
        }
        return hash.get(head);
    }

    public static Node copyListWithRand2(Node head) {
        if (head == null) {
            return null;
        }

        Node cur = head;
        while (cur != null) {
            Node next = cur.next;
            cur.next = new Node(cur.value);
            cur.next.next = next;
            cur = next;
        }

        cur = head;
        while (cur != null) {
            if (cur.rand != null) {
                cur.next.rand = cur.rand.next;
            } else {
                cur.next.rand = null;
            }
            cur = cur.next.next;

        }

        cur = head;
        Node res = head.next;
        while (cur != null && cur.next != null) {
            Node next = cur.next;
            cur.next = cur.next.next;
            cur = next;
        }

        return res;
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // for test
    public static void printRandLinkedList(Node head) {
        Node cur = head;
        System.out.print("order: ");
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println();
        cur = head;
        System.out.print("rand:  ");
        while (cur != null) {
            System.out.print(cur.rand == null ? "- " : cur.rand.value + " ");
            cur = cur.next;
        }
        System.out.println();
    }


    public static void main(String[] args) {
        Node head = null;
        Node res1 = null;
        Node res2 = null;
        printRandLinkedList(head);
        res1 = copyListWithRand1(head);
        printRandLinkedList(res1);
        res2 = copyListWithRand2(head);
        printRandLinkedList(res2);
        printRandLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);

        head.rand = head.next.next.next.next.next; // 1 -> 6
        head.next.rand = head.next.next.next.next.next; // 2 -> 6
        head.next.next.rand = head.next.next.next.next; // 3 -> 5
        head.next.next.next.rand = head.next.next; // 4 -> 3
        head.next.next.next.next.rand = null; // 5 -> null
        head.next.next.next.next.next.rand = head.next.next.next; // 6 -> 4

        System.out.println("原始链表：");
        printRandLinkedList(head);
        System.out.println("=========================");
        res1 = copyListWithRand1(head);
        System.out.println("方法一的拷贝链表：");
        printRandLinkedList(res1);
        System.out.println("=========================");
        res2 = copyListWithRand2(head);
        System.out.println("方法二的拷贝链表：");
        printRandLinkedList(res2);
        System.out.println("=========================");
        System.out.println("经历方法二拷贝之后的原始链表：");
        printRandLinkedList(head);
        System.out.println("=========================");

    }

    /*
    =========================
    原始链表：
    order: 1 2 3 4 5 6
    rand:  6 6 5 3 - 4
    =========================
    方法一的拷贝链表：
    order: 1 2 3 4 5 6
    rand:  6 6 5 3 - 4
    =========================
    方法二的拷贝链表：
    order: 1 2 3 4 5 6
    rand:  6 6 5 3 - 4
    =========================
    经历方法二拷贝之后的原始链表：
    order: 1 2 3 4 5 6
    rand:  6 6 5 3 - 4
    =========================
    */

}
