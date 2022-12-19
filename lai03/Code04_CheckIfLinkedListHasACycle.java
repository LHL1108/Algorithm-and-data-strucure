package lai03;
/*
[Question]
    make sure if there is a circle in the linked list
[Idea]
    fast slow pointer, if there is no circle, fast will reach the end, otherwise ,fast pointer will reach the slow pointer
[Construction]
    cleaning check
        null -> false
    initialization
        fast
        slow
    until fast reaches the end
        fast move 2 steps
        slow move 1 steps
        if they meet
            return true
    return false
[Complexity]
    Time: O(N), because we traverse the linked list
    Space: O(1), because we only apply for several variables
[Notice]
    fast must meets slow, because the difference is 1 step, is like slow stand still and only fast move
    there couldn't be a circle when only 1 element, but 2 it can.
*/

public class Code04_CheckIfLinkedListHasACycle {

    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null ) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    static class ListNode {
        public int value;
        public ListNode next;

        public ListNode(int value) {
            this.value = value;
            next = null;
        }
    }



    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        a.next = b;
        b.next = c;
        c.next = a;
        boolean res = hasCycle(a);
        System.out.println(res);
    }
}
