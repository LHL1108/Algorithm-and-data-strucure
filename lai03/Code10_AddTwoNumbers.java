package lai03;
/*
[Question]
    add two reversed numbers in linked list form, return a linked list form answer like this
    1-2-7 , 1-3-4  ->  2-5-1-1
[Idea]
    reverse two linked list and add two numbers and carry term
[Construction]
    null
    reverse link1
    reverse link2
    add
    update carry
[Complexity]
    Time: O(max(N, M)), we need to calculate until the longer one end
    Space: O(max(N, M)) , we create a new linked list
[Notice]
    clear the meaning of the question
    figure out while loop stopping condition
    consider carry part and add it to the val correctly
*/

public class Code10_AddTwoNumbers {
    public static class ListNode {
        public int value;
        public ListNode next;

        public ListNode(int value) {
            this.value = value;
            next = null;
        }
    }



    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            int val = 0;
            if (l1 != null) {
                val += l1.value;
                l1 = l1.next;
            }
            if (l2 != null) {
                val += l2.value;
                l2 = l2.next;
            }
            val += carry;
            cur.next = new ListNode((val) % 10);
            cur = cur.next;
            carry = val / 10;
        }

        return dummy.next;
    }



    public static void main(String[] args) {

        ListNode a = new ListNode(9);
        ListNode b = new ListNode(8);

        ListNode d = new ListNode(1);
        ListNode e = new ListNode(1);

        a.next = b;

        d.next = e;

        // 98 , 11  -> 001
        ListNode cur = addTwoNumbers(a, d);
        while (cur != null) {
            System.out.println(cur.value);
            cur = cur.next;
        }
    }


}
