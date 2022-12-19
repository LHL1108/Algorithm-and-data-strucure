package lai03;
/*
[Question]
    find the middle node of the linked list
[Idea]
    slow fast pointer
[Construction]
    initialization
        fast
        slow
    move until fast reaches the end
        fast move two steps
        slow move one step
    return slow
[Complexity]
    Time: O(N), because it traverse the linked list
    Space: O(1), we only apply for several variables
[Notice]
    while loop termination condition
*/


public class Code03_MiddleNodeOfLinkedList {
    static class ListNode {
        public int value;
        public ListNode next;

        public ListNode(int value) {
            this.value = value;
            next = null;
        }
    }

    public static ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;

    }


    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        a.next = b;
        b.next = c;
        ListNode res = middleNode(a);
        System.out.println(res.value);
    }

}
