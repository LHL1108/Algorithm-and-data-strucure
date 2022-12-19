package lai03;
/*
[Question]
    remove all target value elements from a linked list
[Idea]
    use dummy node, then traverse and remove the start part and remove the target elements in the middle part
[Construction]
    create a dummy node
    connect it with head
    traverse linked list
    remove all target parts
[Notice]
    use dummy node to change 2 kinds of operations into 1
[Complexity]
    Time: O(N), traverse
    Space: O(1), operate on linked list itself
*/

public class Code12_RemoveLinkedListElements {
    public static class ListNode {
        public int value;
        public ListNode next;

        public ListNode(int value) {
            this.value = value;
            next = null;
        }
    }


    public static ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;
        while (cur != null) {
            if (cur.value == val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return dummy.next;
    }


    public static void main(String[] args) {

        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(2);
        ListNode e = new ListNode(4);

        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;

        int target = 2;

        ListNode cur = removeElements(a, target);
        // 134
        while (cur != null) {
            System.out.println(cur.value);
            cur = cur.next;
        }

    }



}
