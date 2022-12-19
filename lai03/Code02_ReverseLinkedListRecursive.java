package lai03;
/*
[Question]
    use recursive method to reverse a linked list
[Idea]
    downsize the problem, change reverse the whole linked list into reverse from 1-n, and change the pointer between 0 and 1-n reversed list
    and recursively do it
[Construction]
    cleaning check
    base case
    recursive rule
    return
[Complexity]
    Time: O(N), because there is a stack pushing process
    Space: O(N), because there is a stack pushing process
[Notice]
    how to change direction after recursion
*/

public class Code02_ReverseLinkedListRecursive {

    static class ListNode {
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

        ListNode newHead = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
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
