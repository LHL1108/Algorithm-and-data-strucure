package class12;

public class Code03_IsBalanced {
    /*
    要求：判断一颗二叉树是否为平衡二叉树。 平衡二叉树定义：每一颗子树的左子树高度和右子树高度的差值不超过1
    核心思路：平衡二叉树的前提
                左右子树均是平衡二叉树
                左右子树的高度差不超过1
    代码思路：
        主函数
            调用递归

        Info类
            明确解题的必要属性：
                是否为平衡二叉树
                高度

        递归函数
            空
                是平衡二叉树，高度为0
            非空
                获取子树信息
                    左
                    右
                计算该节点数据
                    是否为平衡二叉树
                        左右字数都是且高度相差不到1
                    高度
                        左右子数最大高度+1
            返回信息
    */

    public static boolean isBalanced2(Node head) {
        return process(head).isBalance;
    }

    public static class Info{
        int height;
        boolean isBalance;

        public Info(int h, boolean i) {
            height = h;
            isBalance = i;
        }
    }

    public static Info process(Node head) {
        if (head == null) {
            return new Info(0, true);
        }

        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);

        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isBalance = true;
        if(leftInfo.isBalance == false) {
            isBalance = false;
        }
        if(rightInfo.isBalance == false) {
            isBalance = false;
        }
        if(Math.abs(leftInfo.height - rightInfo.height) > 1) {
            isBalance = false;
        }
        return new Info(height, isBalance);
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

    public static boolean isBalanced1(Node head) {
        boolean[] ans = new boolean[1];
        ans[0] = true;
        process1(head, ans);
        return ans[0];
    }

    public static int process1(Node head, boolean[] ans) {
        if (!ans[0] || head == null) {
            return -1;
        }
        int leftHeight = process1(head.left, ans);
        int rightHeight = process1(head.right, ans);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            ans[0] = false;
        }
        return Math.max(leftHeight, rightHeight) + 1;
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
            if (isBalanced1(head) != isBalanced2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("success!");
    }


}
