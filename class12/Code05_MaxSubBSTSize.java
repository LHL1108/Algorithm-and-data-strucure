package class12;
import java.util.ArrayList;

/*
要求： 返回一颗二叉树的最大搜索子树的节点数
思路：
      树形dp问题，用递归方法
      整理最大搜索子树的几种可能性
        不经过头节点
            在左 在右
        经过头节点
            如果左右子树均为搜索二叉树且左右子树极值均满足条件
            则最大搜索子树节点数为整体节点数
      所需信息为
        子树最大搜索子树节点数
        子树最大值
        子树最小值
        整体节点数
        左右子树是否为搜索二叉树（可以通过1和4是否相等来判断，化简掉）
*/



public class Code05_MaxSubBSTSize {


    public static int maxSubBSTSize2(Node head){
        if (head == null) {
            return 0;
        }
        return process(head).maxbstnodes;
    }

    public static class Info{
        public int max;
        public int min;
        public int nodes;
        public int maxbstnodes;

        public Info(int ma, int mi, int no, int maxbst){
            max = ma;
            min = mi;
            nodes = no;
            maxbstnodes = maxbst;
        }
    }

    public static Info process(Node head){
        if (head == null) {
            return null;
        }

        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);

        int max = head.value;
        int min = head.value;
        int nodes = 1;
        int maxbstnodes = 0;

        if (leftInfo != null) {
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
            nodes += leftInfo.nodes;
        }

        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
            nodes += rightInfo.nodes;
        }

        int p1 = -1;
        if (leftInfo != null) {
            p1 = leftInfo.maxbstnodes;
        }
        int p2 = -1;
        if (rightInfo != null) {
            p2 = rightInfo.maxbstnodes;
        }

        int p3 = -1;
        boolean isleftBST = leftInfo == null ? true : (leftInfo.maxbstnodes == leftInfo.nodes);
        boolean isrightBST = rightInfo == null ? true : (rightInfo.maxbstnodes == rightInfo.nodes);
        boolean isleftvalue = leftInfo == null ? true : (leftInfo.max < head.value);
        boolean isrightvalue =rightInfo == null ? true : (rightInfo.min > head.value);
        if (isleftBST && isrightBST && isleftvalue && isrightvalue){
            int leftnodes = leftInfo == null ? 0 : leftInfo.nodes;
            int rightnodes = rightInfo == null ? 0 : rightInfo.nodes;
            p3 = leftnodes + rightnodes + 1;
        }
        maxbstnodes = Math.max(Math.max(p1, p2), p3);

        return new Info(max, min, nodes, maxbstnodes);
    }




































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

    public static int maxSubBSTSize1(Node head) {
        if (head == null) {
            return 0;
        }
        int h = getBSTSize(head);
        if (h != 0) {
            return h;
        }
        return Math.max(maxSubBSTSize1(head.left), maxSubBSTSize1(head.right));
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
            if (maxSubBSTSize1(head) != maxSubBSTSize2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
