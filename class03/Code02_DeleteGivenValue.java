package class03;

public class Code02_DeleteGivenValue {

    public static class Node {
        public int value;
        public Node next;
        public Node (int data) {
            value = data;
        }
    }

    public static Node deleteGivenValue(Node head, int val) {

        while (head != null) {
            if (head.value != val) {
                break;
            } else {
                head = head.next;
            }
        }

        Node pre = head;
        Node cur = head;
        while (cur != null) {
            if (cur.value == val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    public static void printLinkedList(Node head) {
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node a = new Node(1);
        Node b = new Node(2);
        Node c = new Node(3);
        Node d = new Node(3);
        Node e = new Node(4);

        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        printLinkedList(a);

        int val = 1;
        a = deleteGivenValue(a, val);
        printLinkedList(a);
    }
}