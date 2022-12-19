package lai03;
/*
[Question]
    check the linked list if it is palindrome
[Idea]
    find mid and reverse right part, iterate compare each node
[Construction]
    find mid
    reverse
    compare
    reverse back
    return
[Complexity]
    Time: O(N), because we traverse the linked list to reverse and compare
    Space: O(1), all the operation are on the linked list
[Notice]
    resume linked list after get the result
*/


public class Code11_CheckIfLinkedListIsPalindrome {
    public static class ListNode {
        public int value;
        public ListNode next;

        public ListNode(int value) {
            this.value = value;
            next = null;
        }
    }

    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode mid = findMid(head);
        ListNode midNext = mid.next;
        mid.next = null;
        ListNode rTail = reverse(midNext);
        ListNode l = head;
        ListNode r = rTail;
        boolean res = true;
        while (l != null && r != null) {
            if (l.value != r.value){
                res = false;
                break;
            } else {
                l = l.next;
                r = r.next;
            }
        }
        midNext = reverse(rTail);
        mid.next = midNext;
        return res;
    }


    public static ListNode findMid(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static ListNode reverse(ListNode head) {
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

    //1221
    //123 21
    public static void main(String[] args) {

        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(2);
        ListNode e = new ListNode(1);

        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;

        // true
        System.out.println(isPalindrome(a));
    }

}
