package class12;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
/*
主函数
    调用递归
    返回

Info类
    初始化
        是否为搜索二叉树
        最大值
        最小值
    构造方法

递归函数
    空
        返回null
    非空
        base case
            有空返空
        获取信息
            左子信息
            右子信息
        整合信息
            初始自身
                max min isBST
            更新最大值
                自身
                比左
                比右
            更新最小值
                自身
                比左
                比右
            更新是否为搜索二叉树
                是
                违反规则
                    左子树存在但不是
                    右子树存在但不是
                    左子树存在但其最大值大于等于当前节点
                    右子树存在但其最小值小于等于当前节点
        返回Info
*/

public class Code02_IsBST {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }


    public static boolean isBST2(Node head) {
        if (head == null) {
            return true;
        }
        return process(head).isBSTree;
    }

    public static class Info {
        public int min;
        public int max;
        public boolean isBSTree;

        public Info(int mi, int ma, boolean i){
            min = mi;
            max = ma;
            isBSTree = i;
        }
    }

    public static Info process(Node head) {
        if (head == null) {
            return null;
        }

        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);

        int min = head.value;
        int max = head.value;
        boolean isBSTree = true;

        if(leftInfo != null) {
            min = Math.min(min, leftInfo.min);
        }
        if(rightInfo != null) {
            min = Math.min(min, rightInfo.min);
        }

        if(leftInfo != null) {
            max = Math.max(max, leftInfo.max);
        }
        if(rightInfo != null) {
            max = Math.max(max, rightInfo.max);
        }

        if(leftInfo != null && !leftInfo.isBSTree) {
            isBSTree = false;
        }
        if(rightInfo != null && !rightInfo.isBSTree) {
            isBSTree = false;
        }
        if(leftInfo != null && leftInfo.max >= head.value) {
            isBSTree = false;
        }
        if(rightInfo != null && rightInfo.min <= head.value) {
            isBSTree = false;
        }

        return new Info(min, max, isBSTree);
    }








    public static boolean isBST1(Node head) {
        if (head == null) {
            return true;
        }
        ArrayList<Node> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return false;
            }
        }
        return true;
    }

    public static void in(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
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
            if (isBST1(head) != isBST2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
