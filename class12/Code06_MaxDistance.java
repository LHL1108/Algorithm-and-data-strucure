package class12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

    /*
    要求: 求二叉树的最大路径距离
    思路：
        使用二叉树迭代法
        最大距离有两种可能性：不经过头节点/经过头节点
        所以需要获取的信息为：子树最大路径距离、树的高度
        然后通过子树信息整合本节点信息，向上递归即可
    代码思路：
        主函数
            调用递归，返回子树最大距离
        Info类
            子树最大距离
            高度
        递归函数
            空
                返回0，0
            获取左子信息
            获取右子信息
            获取本节点信息
                高度
                    左子/右子高度最大值 + 1
                子树最大距离
                    左子高度+右子高度+1/左子最大距离/右子最大距离最大值
    */


public class Code06_MaxDistance {

    public static int maxDistance2(Node head){
        return process(head).maxDis;
    }

    public static class Info{
        public int maxDis;
        public int height;

        public Info(int m, int h){
            maxDis = m;
            height = h;
        }
    }

    public static Info process(Node head) {
        if (head == null) {
            return new Info(0, 0);
        }

        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);

        int p1 = leftInfo.maxDis;
        int p2 = rightInfo.maxDis;
        int p3 = leftInfo.height + rightInfo.height + 1;
        int maxDis = Math.max(Math.max(p1, p2), p3);

        int height = Math.max(leftInfo.height, rightInfo.height) + 1;

        return new Info(maxDis, height);
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

    public static int maxDistance1(Node head) {
        if (head == null) {
            return 0;
        }
        ArrayList<Node> arr = getPrelist(head);
        HashMap<Node, Node> parentMap = getParentMap(head);
        int max = 0;
        for (int i = 0; i < arr.size(); i++) {
            for (int j = i; j < arr.size(); j++) {
                max = Math.max(max, distance(parentMap, arr.get(i), arr.get(j)));
            }
        }
        return max;
    }

    public static ArrayList<Node> getPrelist(Node head) {
        ArrayList<Node> arr = new ArrayList<>();
        fillPrelist(head, arr);
        return arr;
    }

    public static void fillPrelist(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        arr.add(head);
        fillPrelist(head.left, arr);
        fillPrelist(head.right, arr);
    }

    public static HashMap<Node, Node> getParentMap(Node head) {
        HashMap<Node, Node> map = new HashMap<>();
        map.put(head, null);
        fillParentMap(head, map);
        return map;
    }

    public static void fillParentMap(Node head, HashMap<Node, Node> parentMap) {
        if (head.left != null) {
            parentMap.put(head.left, head);
            fillParentMap(head.left, parentMap);
        }
        if (head.right != null) {
            parentMap.put(head.right, head);
            fillParentMap(head.right, parentMap);
        }
    }

    public static int distance(HashMap<Node, Node> parentMap, Node o1, Node o2) {
        HashSet<Node> o1Set = new HashSet<>();
        Node cur = o1;
        o1Set.add(cur);
        while (parentMap.get(cur) != null) {
            cur = parentMap.get(cur);
            o1Set.add(cur);
        }
        cur = o2;
        while (!o1Set.contains(cur)) {
            cur = parentMap.get(cur);
        }
        Node lowestAncestor = cur;
        cur = o1;
        int distance1 = 1;
        while (cur != lowestAncestor) {
            cur = parentMap.get(cur);
            distance1++;
        }
        cur = o2;
        int distance2 = 1;
        while (cur != lowestAncestor) {
            cur = parentMap.get(cur);
            distance2++;
        }
        return distance1 + distance2 - 1;
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
            if (maxDistance1(head) != maxDistance2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
