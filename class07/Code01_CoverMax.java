package class07;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Code01_CoverMax {
    /*
    要求：有一些线段，用数组[开始位置,结束位置表示]，求这些线段最大的重合数量，如[0, 3],[0,2],[1,3]的最大重合数是3，重合区间是[1,2]
         前提，每个线段最小长度至少为1，且重合区域也至少为1,输入为一个二维数组如
         [0][3]
         [0][2]
         [1][3]

    思路：
        [堆方法]
        1.将所有线段根据起点由小到大排序
        2.针对每个线段，记录其起始位置start,调整以start为起始点的贯穿堆heap，记录其最大值，最大的最大值即是答
        3.调整的过程为：对于每个新的start,将堆中小于start的数去掉，并添加进该线段的end，计算堆的大小
        4.因为结尾位置都小于新start了，说明一定不会贯穿，没必要留在新堆中，而之前的结尾，因为开始位置比现在位置还要小，所以其大于start的end可以保留

        [常规遍历方法]
        1.求出所有线段的最小最大值
        2.对于期间的每一个值，遍历所有数组，只要start小于p且end大于p，cur+1，计算完之后求为该p的覆盖数
        3.p的全局最大值即为answer

    易错点：
        1.定义类的方法，public关键字 static关键字
        2.比较器的定义方法，static关键字，Comparator关键字，里面的传参
        3.Line数组的构建方法
        4.比较器的拼写

    */

    public static class Line{
        public int start;
        public int end;
        public Line(int s, int e){
            start = s;
            end = e;
        }
    }

    public static class StartComparator implements Comparator<Line> {
        @Override
        public int compare(Line o1, Line o2) {
            return o1.start - o2.start;
        }
    }

    public static int maxCover2(int[][] m) {
        // 将其根据起始值排序
        Line[] lines = new Line[m.length];
        for (int i = 0; i < m.length; i++) {
            lines[i] = new Line(m[i][0], m[i][1]);
        }
        Arrays.sort(lines, new StartComparator());

        // 根据结尾值构建堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int covers = 0;
        for (int i = 0; i < lines.length; i++) {
            int s = lines[i].start;
            while (!heap.isEmpty() && heap.peek() <= s){
                heap.poll();
            }
            heap.add(lines[i].end);
            covers = Math.max(covers, heap.size());
        }
        return covers;

    }


    //////////////////////////////////////////////////////////////////
    // for test
    public static int maxCover1(int[][] lines) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        // 计算全局最小最大值
        for (int i = 0; i < lines.length; i++) {
            min = Math.min(min, lines[i][0]);
            max = Math.max(max, lines[i][01]);
        }
        int cover = 0;
        for (double p = min + 0.5; p < max; p +=1) {
            int cur = 0;
            for (int i = 0; i < lines.length; i++) {
                if(lines[i][0] < p && lines[i][1] > p) {
                    cur++;
                }
            }
            cover = Math.max(cover, cur);
        }
        return cover;
    }

    // for test
    public static int[][] generateLines(int N, int L, int R) {
        int size = (int) (Math.random() * N) + 1;
        int[][] ans = new int[size][2];
        for (int i = 0; i < size; i++) {
            int a = L + (int) (Math.random() * (R - L + 1));
            int b = L + (int) (Math.random() * (R - L + 1));
            if (a == b) {
                b = a + 1;
            }
            ans[i][0] = Math.min(a, b);
            ans[i][1] = Math.max(a, b);
        }
        return ans;
    }

    public static void main(String[] args) {

        Line l1 = new Line(4, 9);
        Line l2 = new Line(1, 4);
        Line l3 = new Line(7, 15);
        Line l4 = new Line(2, 4);
        Line l5 = new Line(4, 6);
        Line l6 = new Line(3, 7);

        // 底层堆结构，heap
        PriorityQueue<Line> heap = new PriorityQueue<>(new StartComparator());
        heap.add(l1);
        heap.add(l2);
        heap.add(l3);
        heap.add(l4);
        heap.add(l5);
        heap.add(l6);

        while (!heap.isEmpty()) {
            Line cur = heap.poll();
            System.out.println(cur.start + "," + cur.end);
        }

        System.out.println("test begin");
        int N = 100;
        int L = 0;
        int R = 200;
        int testTimes = 200;
        for (int i = 0; i < testTimes; i++) {
            int[][] lines = generateLines(N, L, R);
            int ans1 = maxCover1(lines);
            int ans2 = maxCover2(lines);
            if (ans1 != ans2) {
                System.out.println("测试失败!");
            }
        }
        System.out.println("测试成功！");
    }


}
