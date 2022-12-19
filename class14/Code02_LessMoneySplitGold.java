package class14;
import java.util.PriorityQueue;
/*
要求：切分一根金条，每次切分都花费总长度的代价，求切分一根金条的最小代价，忽略顺序
	  例如 [3, 1, 2]  第一刀先将3和1,2切开，花费6，再将1,2切开花费3，总花费为9，为最小 代价。
思路：每次只分最小的两块，分后合并，由小到大，直至明确所有金条的分法，通过小根堆实现
代码思路
	初始化
		创建小根堆
		小根堆赋值
		基础参数
	在合并到只剩下一个前
		取出两个最小的
		合并
		计算代价
    将合并金条放入堆中
*/

public class Code02_LessMoneySplitGold {
    public static int lessMoney2(int[] arr) {
        PriorityQueue<Integer> pQ = new PriorityQueue<Integer>();
        for (int i = 0; i < arr.length; i++) {
            pQ.add(arr[i]);
        }
        int cur = 0;
        int sum = 0;
        while (pQ.size() > 1) {
            cur = pQ.poll() + pQ.poll();
            sum += cur;
            pQ.add(cur);
        }
        return sum;
    }


    // 纯暴力！
    public static int lessMoney1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return process(arr, 0);
    }

    // 等待合并的数都在arr里，pre之前的合并行为产生了多少总代价
    // arr中只剩一个数字的时候，停止合并，返回最小的总代价
    public static int process(int[] arr, int pre) {
        if (arr.length == 1) {
            return pre;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                ans = Math.min(ans, process(copyAndMergeTwo(arr, i, j), pre + arr[i] + arr[j]));
            }
        }
        return ans;
    }

    public static int[] copyAndMergeTwo(int[] arr, int i, int j) {
        int[] ans = new int[arr.length - 1];
        int ansi = 0;
        for (int arri = 0; arri < arr.length; arri++) {
            if (arri != i && arri != j) {
                ans[ansi++] = arr[arri];
            }
        }
        ans[ansi] = arr[i] + arr[j];
        return ans;
    }


    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTime = 100000;
        int maxSize = 6;
        int maxValue = 1000;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            if (lessMoney1(arr) != lessMoney2(arr)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
