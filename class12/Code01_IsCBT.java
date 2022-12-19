package class12;
import java.util.LinkedList;
import java.util.Queue;

/*
要求：给定一个二叉树的头节点，判断这颗二叉树是否为完全二叉树。完全二叉树定义，不是满的就是在变满的路上。
思路：
    完全二叉树的违反规则有两条：
        有右无左
        在遇到不完全节点后，后面的节点不是叶子节点
    在此基础上，使用队列进行逐层遍历进行判断即可
代码思路：
    空
        判是
    非空
        初始化
            队列（遍历用）
            是否遇到过左右子不双全的节点的标志
            左右节点
        运行（队列不为空）
            弹出节点
            判断结果
                左空右有 | 遇到不爽全节点又发现其不是叶子节点  ——> 判非
            为后续做准备
                有左添加左
                有右添加右
            判断左右不爽全
        判是
*/

public class Code01_IsCBT {


    public static boolean isCBT1(Node head) {
        if(head == null) {
            return true;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Node l = null;
        Node r = null;
        boolean leaf = false;

        while(!queue.isEmpty()){
            Node node = queue.poll();
            l = node.left;
            r = node.right;
            if((l == null && r != null)
                    ||
                leaf && (l != null || r != null)){
                return false;
            }
            if(l != null) {
                queue.add(l);
            }
            if(r != null) {
                queue.add(r);
            }
            if(l == null || r == null) {
                leaf = true;
            }

        }
        return true;
    }

    public static boolean isCBT2(Node head) {
        if (head == null) {
            return true;
        }
        return process(head).isCBT;
    }

    // 对每一棵子树，是否是满二叉树、是否是完全二叉树、高度
    public static class Info {
        public boolean isFull;
        public boolean isCBT;
        public int height;

        public Info(boolean full, boolean cbt, int h) {
            isFull = full;
            isCBT = cbt;
            height = h;
        }
    }

    public static Info process(Node X) {
        if (X == null) {
            return new Info(true, true, 0);
        }
        Info leftInfo = process(X.left);
        Info rightInfo = process(X.right);



        int height = Math.max(leftInfo.height, rightInfo.height) + 1;


        boolean isFull = leftInfo.isFull
                &&
                rightInfo.isFull
                && leftInfo.height == rightInfo.height;


        boolean isCBT = false;
        if (isFull) {
            isCBT = true;
        } else { // 以x为头整棵树，不满
            if (leftInfo.isCBT && rightInfo.isCBT) {


                if (leftInfo.isCBT
                        && rightInfo.isFull
                        && leftInfo.height == rightInfo.height + 1) {
                    isCBT = true;
                }
                if (leftInfo.isFull
                        &&
                        rightInfo.isFull
                        && leftInfo.height == rightInfo.height + 1) {
                    isCBT = true;
                }
                if (leftInfo.isFull
                        && rightInfo.isCBT && leftInfo.height == rightInfo.height) {
                    isCBT = true;
                }


            }
        }
        return new Info(isFull, isCBT, height);
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
        System.out.println("success!");
    }

}