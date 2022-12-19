package class13;

import java.util.ArrayList;

/*
要求： 返回二叉树的最大搜索二叉树的头节点
思路：
    树型递归算法
    分两种
        头节点不是（直接更新）
            在左
            在右
        头节点是
            更新各值

*/


public class Code02_MaxSubBSTHead {
    public static Node maxSubBSTHead2(Node head) {
        if (head == null) {
            return null;
        }
        return process(head).maxBSThead;
    }

    public static class Info {
        public int max;
        public int min;
        public Node maxBSThead;
        public int maxBSTnodes;

        public Info(int ma, int mi, Node mh, int mn){
            max = ma;
            min = mi;
            maxBSThead = mh;
            maxBSTnodes = mn;
        }
    }

    public static Info process(Node head){
        if (head == null) {
            return null;
        }

        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);

        int X = head.value;
        int max = X;
        int min = X;
        Node maxBSThead = null;
        int maxBSTnodes = 0;
        if (leftInfo != null) {
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
            maxBSThead = leftInfo.maxBSThead;
            maxBSTnodes = leftInfo.maxBSTnodes;
        }
        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
            if (rightInfo.maxBSTnodes > maxBSTnodes) {
                maxBSThead = rightInfo.maxBSThead;
                maxBSTnodes = rightInfo.maxBSTnodes;
            }
        }
        if ((leftInfo == null ? true : (leftInfo.maxBSThead == head.left) && leftInfo.max < X) && (rightInfo == null ? true : (rightInfo.maxBSThead == head.right) && rightInfo.min > X)) {
            maxBSThead = head;
            maxBSTnodes = (leftInfo == null ? 0 : leftInfo.maxBSTnodes) + (rightInfo == null ? 0 : rightInfo.maxBSTnodes) + 1;
        }

        return new Info(max, min, maxBSThead, maxBSTnodes);
    }




    // for test
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static int getBSTSize(Node head) {
        if (head == null) {
            return 0;
        }
        ArrayList<Node> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return 0;
            }
        }
        return arr.size();
    }

    public static void in(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }

    public static Node maxSubBSTHead1(Node head) {
        if (head == null) {
            return null;
        }
        if (getBSTSize(head) != 0) {
            return head;
        }
        Node leftAns = maxSubBSTHead1(head.left);
        Node rightAns = maxSubBSTHead1(head.right);
        return getBSTSize(leftAns) >= getBSTSize(rightAns) ? leftAns : rightAns;
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
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxSubBSTHead1(head) != maxSubBSTHead2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
