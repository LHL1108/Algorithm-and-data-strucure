package class12;
    /*
    要求： 判断一颗二叉树是否为满二叉树
    思路： 满二叉树的节点数 = 2^高度 - 1
          所以使用迭代法，所需信息为高度、节点数
    代码思路：
        主函数
            调用递归函数
            返回 节点数和高度是否符合规律
        Info类
            高度
            节点数
        递归函数
            baseCase
                进空，返回0，0
            递归
                获取子树信息
                    左
                    右
                整合节点信息
                    高度
                        左右最大+1
                    节点数
                        左+右+1
            返回
    */

public class Code04_IsFull {

    public static boolean isFull2(Node head) {
        if (head == null){
            return true;
        }
        return (1 << process(head).height) - 1 == process(head).nodes;
    }

    public static class Info{
        public int height;
        public int nodes;

        public Info(int h, int n){
            height = h;
            nodes = n;
        }
    }

    public static Info process(Node head){
        if (head == null) {
            return new Info(0, 0);
        }

        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);

        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int nodes = leftInfo.nodes + rightInfo.nodes + 1;

        return new Info(height, nodes);
    }










    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static boolean isFull1(Node head) {
        if (head == null) {
            return true;
        }
        int height = h(head);
        int nodes = n(head);
        return (1 << height) - 1 == nodes;
    }

    public static int h(Node head) {
        if (head == null) {
            return 0;
        }
        return Math.max(h(head.left), h(head.right)) + 1;
    }

    public static int n(Node head) {
        if (head == null) {
            return 0;
        }
        return n(head.left) + n(head.right) + 1;
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
            if (isFull1(head) != isFull2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("success!");
    }

}
