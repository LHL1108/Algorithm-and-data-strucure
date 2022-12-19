package lai03;
/*
[Question]
    sort a listed link with merge sort method
[Idea]
    in split part, it is same with array merge sort, just call itself
    in merge part, we use linked list merge, dummy node, link smaller one
[Construction]
    find mid
    recursion left part and write part
    link merge them
[Complexity]
    Time: O(NlogN), because we run logN times O(N) traverse.
    Space: O(logN), because we there is no extra space in merge part, we only use call stack, space is logN for recursion
[Notice]
    we should break the linked list after we find the mid point
    return slow when we find mid point
    notice about create a class
*/

public class Code09_MergeSortLinkedList {
    public static class ListNode {
        public int value;
        public ListNode next;

        public ListNode(int value) {
            this.value = value;
            next = null;
        }
    }

    public static ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode mid = findMid(head);
        ListNode midNext = mid.next;
        mid.next = null;
        ListNode l = mergeSort(head);
        ListNode r = mergeSort(midNext);
        return merge(l, r);
    }

    public static ListNode findMid(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static ListNode merge(ListNode one, ListNode two) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (one != null && two != null) {
            if (one.value <= two.value) {
                cur.next = one;
                one = one.next;
                cur = cur.next;
            } else {
                cur.next = two;
                two = two.next;
                cur = cur.next;
            }
        }
        if (one != null) {
            cur.next = one;
        }
        if (two != null) {
            cur.next = two;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(3);
        ListNode c = new ListNode(2);
        ListNode d = new ListNode(6);
        ListNode e = new ListNode(5);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        ListNode cur = mergeSort(a);
        // expect 12356
        while (cur != null) {
            System.out.println(cur.value);
            cur = cur.next;
        }
    }


}
