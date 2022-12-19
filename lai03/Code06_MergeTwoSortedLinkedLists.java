package lai03;
/*
[Question]
    Merge 2 linked list in order 1-4-6,  2-5  ->  1-2-4-5-6
[Idea]
    move the smaller one, dummy node
[Construction]
    cleaning check
    Initialization
        create dummy
        create i, j
    while neither array out of bounds
        compare
        move the smaller one
    move the rest
    return dummy.next
[Complexity]
    Time: O(N), because we traverse the array
    Space: O(1), because we only apply for several variables
[Notice]
    after one array is to the end, we just need to change direction 1 time
*/

public class Code06_MergeTwoSortedLinkedLists {

    static class ListNode {
        public int value;
        public ListNode next;

        public ListNode(int value) {
            this.value = value;
            next = null;
        }
    }

    public static ListNode merge(ListNode one, ListNode two) {
        if (one == null) {
            return two;
        }
        if (two == null) {
            return one;
        }
        ListNode dummy = new ListNode(0);
        ListNode cur1 = one;
        ListNode cur2 = two;
        ListNode cur = dummy;
        while (cur1 != null && cur2 != null) {
            if (cur1.value <= cur2.value) {
                cur.next = cur1;
                cur = cur.next;
                cur1 = cur1.next;
            } else {
                cur.next = cur2;
                cur = cur.next;
                cur2 = cur.next;
            }
        }
        if (cur1 != null) {
            cur.next = cur1;
        } else {
            cur.next = cur2;
        }
        return dummy.next;
    }


    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(3);
        ListNode c = new ListNode(5);
        a.next = b;
        b.next = c;

        ListNode d = new ListNode(2);
        ListNode e = new ListNode(4);
        ListNode f = new ListNode(6);
        d.next = e;
        e.next = f;

        ListNode cur = merge(a, d);
        while (cur != null) {
            System.out.println(cur.value);
            cur = cur.next;
        }
    }



}
