package class13;
import java.util.ArrayList;
import java.util.List;
/*
要求： 一个多叉树，每个节点具有一个快乐值，在父节点和子节点不能同时都来，求这个多叉树的最大快乐值
思路： 最终答案为头节点来或不来的最大值
      对于递归算法
        头节点来时的最大值一定要加下级节点部来的快乐值
        头节点不来时的最大值要加上下级节点来或不来的最大值
代码思路:
    调用函数
        求头节点信息
        比较最大最小值返回

    Info类
        头节点来时的maxhappy
        头节点不来时的maxhappy

    递归函数
        basecase
            来或不来都是0
        获取子节点信息
            左右
        更新属性
            初始化
                不来0
                来头节点value
            遍历每个子节点
                更新来
                    +子节点不来的maxhappy
                更新来
                    + 子节点来或不来的最大值
        返回
*/


public class Code04_MaxHappy {

    public static int maxHappy2(Employee boss) {
        Info allinfo = process(boss);
        return Math.max(allinfo.yes, allinfo.no);
    }

    public static class Info{
        public int yes;
        public int no;

        public Info(int y, int n){
            yes = y;
            no = n;
        }
    }

    public static Info process(Employee head) {
        if (head == null) {
            return new Info(0, 0);
        }

        int no = 0;
        int yes = head.happy;
        for (Employee next : head.nexts) {
            Info nextInfo = process(next);
            yes += nextInfo.no;
            no += Math.max(nextInfo.no, nextInfo.yes);
        }

        return new Info(yes, no);

    }





    public static class Employee {
        public int happy;
        public List<Employee> nexts;

        public Employee(int h) {
            happy = h;
            nexts = new ArrayList<>();
        }

    }

    public static int maxHappy1(Employee boss) {
        if (boss == null) {
            return 0;
        }
        return process1(boss, false);
    }
    // 当前来到的节点叫cur，
    // up表示cur的上级是否来，
    // 该函数含义：
    // 如果up为true，表示在cur上级已经确定来，的情况下，cur整棵树能够提供最大的快乐值是多少？
    // 如果up为false，表示在cur上级已经确定不来，的情况下，cur整棵树能够提供最大的快乐值是多少？
    public static int process1(Employee cur, boolean up) {
        if (up) { // 如果cur的上级来的话，cur没得选，只能不来
            int ans = 0;
            for (Employee next : cur.nexts) {
                ans += process1(next, false);
            }
            return ans;
        } else { // 如果cur的上级不来的话，cur可以选，可以来也可以不来
            int p1 = cur.happy;
            int p2 = 0;
            for (Employee next : cur.nexts) {
                p1 += process1(next, true);
                p2 += process1(next, false);
            }
            return Math.max(p1, p2);
        }
    }


    // for test
    public static Employee genarateBoss(int maxLevel, int maxNexts, int maxHappy) {
        if (Math.random() < 0.02) {
            return null;
        }
        Employee boss = new Employee((int) (Math.random() * (maxHappy + 1)));
        genarateNexts(boss, 1, maxLevel, maxNexts, maxHappy);
        return boss;
    }

    // for test
    public static void genarateNexts(Employee e, int level, int maxLevel, int maxNexts, int maxHappy) {
        if (level > maxLevel) {
            return;
        }
        int nextsSize = (int) (Math.random() * (maxNexts + 1));
        for (int i = 0; i < nextsSize; i++) {
            Employee next = new Employee((int) (Math.random() * (maxHappy + 1)));
            e.nexts.add(next);
            genarateNexts(next, level + 1, maxLevel, maxNexts, maxHappy);
        }
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxNexts = 7;
        int maxHappy = 100;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            Employee boss = genarateBoss(maxLevel, maxNexts, maxHappy);
            if (maxHappy1(boss) != maxHappy2(boss)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
