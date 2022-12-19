package lai03;
/*
[Question]
    insert a value into a sorted array, 2 , 1-3-5  ->  1-2-3-5
[Idea]
    traverse the linked list, judge the relationship between insert value and cur/cur.next
[Construction]
    insert is on the left
        null
        head is bigger
        return insert
    insert position is on the right
        move to the right position
        change direction
[Complexity]
    Time: O(N), traverse the linked list
    Space: O(1), only apply for several variables
[Notice]
    use .value when compare node's value
*/

public class Code05_InsertInSortedLinkedList {

    public static ListNode insert(ListNode head, int value) {
        ListNode insert = new ListNode(value);
        if (head == null || head.value > value) {
            insert.next = head;
            return insert;
        }

        ListNode cur = head;

        while (cur.next != null && cur.next.value < value) {
            cur = cur.next;
        }
        insert.next = cur.next;
        cur.next = insert;

        return head;
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
        ListNode b = new ListNode(3);
        ListNode c = new ListNode(4);
        a.next = b;
        b.next = c;
        int value = 2;
        ListNode cur = insert(a, value);
        while (cur != null) {
            System.out.println(cur.value);
            cur = cur.next;
        }
    }

}
