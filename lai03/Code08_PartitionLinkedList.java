package lai03;
/*
[Question]
    given a certain value, partition the linked list, move all the smaller element to the left, move others to the right
[Idea]
    set to linked, smaller and larger, visit elements iteratively, if it is smaller, link it to smaller, else link it to larger
[Construction]
    cleaning check
        0,1 -> return arr
    initialization
        smaller (0)
        larger (0)
    iterate
        smaller -> link to smaller, move cur
        equal or larger -> link to larger, move cur
    link smaller  tail to larger head (next)
    point larger tail to null
    return smaller.next
[Complexity]
    Time: O(N), because we traverse the linked list
    Space: O(1), because we only use several variables
[Notice]
    connect and return smallHead.next / largeHead.next
    remember point largeTail to null
    never copy code
*/

public class Code08_PartitionLinkedList {
    public static class ListNode {
        public int value;
        public ListNode next;

        public ListNode(int value) {
            this.value = value;
            next = null;
        }
    }

    public static ListNode partition(ListNode head, int target) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode smallHead = new ListNode(0);
        ListNode largeHead = new ListNode(0);
        ListNode smalltail = smallHead;
        ListNode largetail = largeHead;
        ListNode cur = head;

        while (cur != null) {
            if (cur.value < target) {
                smalltail.next = cur;
                smalltail = smalltail.next;
            } else {
                largetail.next = cur;
                largetail = largetail.next;
            }
            cur = cur.next;
        }
        largetail.next = null;
        smalltail.next = largeHead.next;
        return smallHead.next;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(5);
        ListNode b = new ListNode(1);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(2);
        ListNode e = new ListNode(4);

        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        int target = 4;
        //5-1-3-2-4 target = 4  return 13254
        ListNode cur = partition(a, target);
        while (cur != null) {
            System.out.println(cur.value);
            cur = cur.next;
        }
    }


}
