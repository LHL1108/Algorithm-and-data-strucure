package lai03;

/*
[Question]
    Reorder a linked list, cross-arrange the first and last elements
[Idea]
    find the middle point, reverse the right part, then merge two part
[Construction]
    cleaning check
    find middle part
    reverse right part
    merge them
[Complexity]
[Notice]
*/

public class Code07_ReOrderLinkedList {
    public static class ListNode {
        public int value;
        public ListNode next;

        public ListNode(int value) {
            this.value = value;
            next = null;
        }
    }

    public static ListNode findMid(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public static ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode cur = head;
        ListNode aft = null;
        while (cur != null) {
            aft = cur.next;
            cur.next = pre;
            pre = cur;
            cur = aft;
        }
        return pre;
    }

    public static ListNode merge(ListNode one, ListNode two) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (one != null && two != null) {
            cur.next = one;
            one = one.next;
            cur.next.next = two;
            two = two.next;
            cur = cur.next.next;
        }
        if (one != null) {
            cur.next = one;
        } else {
            cur.next = two;
        }
        return dummy.next;
    }
    public static ListNode reorder(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode mid = findMid(head);
        ListNode tail = reverse(mid.next);
        mid.next = null;
        ListNode newHead = merge(head, tail);
        return newHead;
    }



    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        ListNode e = new ListNode(5);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        ListNode cur = reorder(a);
        while (cur != null) {
            System.out.println(cur.value);
            cur = cur.next;
        }
    }
}
