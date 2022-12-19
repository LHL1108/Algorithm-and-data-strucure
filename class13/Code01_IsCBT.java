package class13;
import java.util.LinkedList;
/*
要求： 判断一颗二叉树是否为完全二叉树（不是满的就是在从左向右变满的路上） isCBT2
思路： 使用递归算法
      把完全二叉树分成4种情况
         左完全，右满，左高右1
         左满，右满，左高右1
         左满，右完全，左右齐高
         左满，右满，左右齐高
      需要信息三点：
         是否满
         是否完全
         高度
代码思路：
    主函数
        调用Info类的是否完全

    Info类
        初始化参数
        构造函数

    递归函数
        基本情况
            满，满，0
        获取左右子信息
        计算
            高度
                左右最大高度
            满
                左右全满且高度相等
            完全
                左完全，右满，左高右1
                左满，右满，左高右1
                左满，右完全，左右齐高
                左满，右满，左右齐高
*/



public class Code01_IsCBT {

    public static boolean isCBT2(Node head) {
        return process(head).iscbt;
    }

    public static class Info{
        public boolean iscbt;
        public boolean isfull;
        public int height;

        public Info(boolean cbt, boolean full, int h){
            iscbt = cbt;
            isfull = full;
            height = h;
        }
    }

    public static Info process(Node head) {
        if (head == null) {
            return new Info(true, true, 0);
        }

        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);

        int height = Math.max(leftInfo.height, rightInfo.height);
        boolean isfull = leftInfo.isfull && rightInfo.isfull && (leftInfo.height == rightInfo.height);
        boolean iscbt = false;

        if(leftInfo.iscbt && rightInfo.isfull && leftInfo.height == rightInfo.height + 1) {
            iscbt = true;
        }
        if(leftInfo.isfull && rightInfo.isfull && leftInfo.height == rightInfo.height + 1) {
            iscbt = true;
        }
        if(leftInfo.isfull && rightInfo.iscbt && leftInfo.height == rightInfo.height) {
            iscbt = true;
        }
        if(leftInfo.isfull && rightInfo.isfull && leftInfo.height == rightInfo.height) {
            iscbt = true;
        }

        return new Info(iscbt, isfull, height);
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


    public static boolean isCBT1(Node head) {
        if (head == null) {
            return true;
        }
        LinkedList<Node> queue = new LinkedList<>();
        // 是否遇到过左右两个孩子不双全的节点
        boolean leaf = false;
        Node l = null;
        Node r = null;
        queue.add(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            l = head.left;
            r = head.right;
            if (
                // 如果遇到了不双全的节点之后，又发现当前节点不是叶节点
                    (leaf && (l != null || r != null)) || (l == null && r != null)

            ) {
                return false;
            }
            if (l != null) {
                queue.add(l);
            }
            if (r != null) {
                queue.add(r);
            }
            if (l == null || r == null) {
                leaf = true;
            }
        }
        return true;
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
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isCBT1(head) != isCBT2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
