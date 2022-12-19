package lai03;
/*
[Question]
    Reverse a linked list Iteratively, 1-2-3-null -> 3-2-1-null
[Idea]
    do it iteratively with two pointer, one saving last one, one saving next one, becasue we couldn't find last element in a linked list
    and we couldn't find next one after we reverse pointer
[Construction]
    cleaning check
        null -> null
        1 element -> head
    initialization
        pre
        cur
        next
    while last one is changed
        save next
        change direction
        move pointer
    return tail
[Complexity]
    Time Complexity: O(N), because we traverse the linked list
    Space Compleity: O(1), because we only apply for several variables
[Notice]
    the first assignment of next is in the loop
    we can use a small scale of data to help us figure out loop stopping condition,
    how to change and move pointer and return what
*/


public class Code01_ReverseLinkedListIterative {
    public static class ListNode {
        public int value;
        public ListNode next;

        public ListNode(int value) {
            this.value = value;
            next = null;
        }
    }

    public static ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode cur = head;
        ListNode next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        a.next = b;
        b.next = c;
        ListNode res = reverse(a);
        ListNode cur = res;
        while (cur != null) {
            System.out.println(cur.value);
            cur = cur.next;
        }
    }
}
