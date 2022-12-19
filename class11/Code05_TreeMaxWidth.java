package class11;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Code05_TreeMaxWidth {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static int maxWidthUseMap(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> q= new LinkedList<>();
        q.add(head);
        HashMap<Node, Integer> hash = new HashMap<>();
        hash.put(head, 1);
        int curLevel = 1;
        int curLevelNodes = 0;
        int max = 0;

        while (!q.isEmpty()){
            Node cur = q.poll();
            int curNodeLevel = hash.get(cur);
            if(cur.left != null) {
                q.add(cur.left);
                hash.put(cur.left, curNodeLevel+1);
            }
            if(cur.right != null) {
                q.add(cur.right);
                hash.put(cur.right, curNodeLevel+1);
            }
            if (curNodeLevel != curLevel){
                curLevelNodes++;
            } else {
                max = Math.max(max, curLevelNodes);
                curLevelNodes = 1;
                curLevel++;
            }
        }
        max = Math.max(max, curLevelNodes);
        return max;
    }

    public static int maxWidthNoMap(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> q = new LinkedList<>();
        q.add(head);
        Node curEnd = head;
        Node nextEnd = null;
        int curLevelNodes = 0;
        int max = 0;

        while(!q.isEmpty()){
            Node cur = q.poll();
            if (cur.left != null) {
                q.add(cur.left);
                nextEnd = cur.left;
            }
            if (cur.right != null) {
                q.add(cur.right);
                nextEnd = cur.right;
            }
            curLevelNodes++;
            if (cur == curEnd) {
                max = Math.max(max, curLevelNodes);
                curLevelNodes = 0;
                curEnd = nextEnd;
            }
        }
        return max;
    }


    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 10;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxWidthUseMap(head) != maxWidthNoMap(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");

    }

}
