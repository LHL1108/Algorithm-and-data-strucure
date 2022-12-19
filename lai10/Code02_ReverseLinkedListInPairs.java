package lai10;
/*
[Question]
    reverse a linked list in pair
    L = 1 -> 2 -> 3 -> null, after reverse is 2 -> 1 -> 3 -> null
[Idea]
    reverse the tail except for first two
[Complexity]
    Time: O(n)
    Space: O(1)
[Notice]
    know what is recursion part
*/

import practice.Code01_StringManipulation;

public class Code02_ReverseLinkedListInPairs {

    public static ListNode reverseInPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode tail = reverseInPairs(head.next.next);
        ListNode newHead = head.next;
        newHead.next = head;
        head.next = tail;
        return newHead;
    }




    public static class ListNode {
        public int value;
        public ListNode next;

        public ListNode(int value) {
            this.value = value;
            next = null;
        }
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        ListNode cur = reverseInPairs(n1);
        while (cur != null) {
            System.out.println(cur.value);
            cur = cur.next;
        }

    }
}
