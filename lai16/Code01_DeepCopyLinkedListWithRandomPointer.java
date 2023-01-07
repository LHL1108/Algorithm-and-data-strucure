package lai16;
import java.util.HashMap;
import java.util.Map;
/*
[question]
    deep copy a linked list, each node has a random pointer, points to null or another node
[idea]
    try to use a hashmap to build the relationship between origin linkedlist and copied one, and avoid to create multiple times
        create next node if needed
        link next node
        create random if needed
        link random
[complexity]
    time: O(n), traverse
    space: O(n), hashmap
[notice]
    when copy random, it is cur.next.random, not cur.random

*/


public class Code01_DeepCopyLinkedListWithRandomPointer {

    public static RandomListNode copy(RandomListNode head) {
        if (head == null) {
            return null;
        }

        RandomListNode dummy = new RandomListNode(0);
        RandomListNode cur = dummy;
        Map<RandomListNode, RandomListNode> map = new HashMap<>();

        while (head != null) {
            // link next
            if (map.get(head) == null) {
                map.put(head, new RandomListNode(head.value));
            }
            cur.next = map.get(head);
            // link random
            if (head.random != null) {
                //create
                if (map.get(head.random) == null) {
                    map.put(head.random, new RandomListNode(head.random.value));
                }
                cur.next.random = map.get(head.random);
            }
            // move
            head = head.next;
            cur = cur.next;
        }
        return dummy.next;
    }


    static class RandomListNode {
        public int value;
        public RandomListNode next;
        public RandomListNode random;
        public RandomListNode(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        RandomListNode a = new RandomListNode(1);
        RandomListNode b = new RandomListNode(2);
        RandomListNode c = new RandomListNode(3);
        a.next = b;
        b.next = c;
        a.random = c;
        RandomListNode res = copy(a);
        // 1 2 3
        System.out.println(res.value);
        System.out.println(res.next.value);
        System.out.println(res.random.value);
    }

}
