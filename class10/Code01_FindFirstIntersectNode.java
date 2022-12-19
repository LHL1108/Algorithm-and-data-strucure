package class10;

public class Code01_FindFirstIntersectNode {
    /*
    一步一步来
    1.阅读代码 √
    2.弄清题意和代码思路 √
    3.撰写要求和思路 √
    4.分步撰写代码

    要求：给定两个链表头节点，返回两个链表相交的第一个节点（链表可能有环可能无环）
    思路：
        1.如果两个链表相交，必定两个都有环，或者两个都无环，如果都不是，必不相交。
        2.所以主函数就是先简单判断下两个头节点的情况，先求loop节点（如无返回null），再求相交节点
        3.两个无环求相交节点的思路为，遍历两个链表，看尾部是否相同，不相同说明不相交，相同，长的先走差值步，再一起走，相遇点即是相交点
        4.两个有环求相交节点的思路为，三种情况，要么为同一个Loop，此时将其去掉还变为无环求相交，若环上有两个loop，走一圈，遇到另一个loop说明相交，返回任意一个loop，否则说明不相交
    易错点：
        找循环节点，记得有无循环节点这码事
    */

    // 先定义节点类

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }

        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);

        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        } else if (loop1 != null && loop2 != null) {
            return bothLoop(head1, loop1, head2, loop2);
        } else {
            return null;
        }
    }

    public static Node getLoopNode(Node head) {
        // 快慢分别从头走，相遇后，快到头，走一步，相遇节点
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        while (slow != fast) {
            if (fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }else{
                return null;
            }

        }
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }

    public static Node noLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        int n = 0;
        Node cur1 = head1;
        Node cur2 = head2;
        while (cur1.next != null) {
            cur1 = cur1.next;
            n++;
        }
        while (cur2.next != null) {
            cur2 = cur2.next;
            n--;
        }
        if (cur1 != cur2) {
            return null;
        }
        cur1 = n > 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;
        n = Math.abs(n);
        while (n > 0) {
            cur1 = cur1.next;
            n--;
        }
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        if (loop1 == loop2) {
            int n = 0;
            Node cur1 = head1;
            Node cur2 = head2;
            while (cur1 != loop1) {
                cur1 = cur1.next;
                n++;
            }
            while (cur2 != loop1) {
                cur2 = cur2.next;
                n--;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n > 0) {
                cur1 = cur1.next;
                n--;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {
            Node cur = loop1;
            while (cur != loop2) {
                cur = cur.next;
                if (cur == loop2) {
                    return loop1;
                }
            }
            return null;
        }
    }

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).value);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

    }
}
