package class24;
import java.util.LinkedList;

/*
要求：在一个数组arr中，有一个长度为w，从左向右运动的滑动窗口，求滑窗最大值组成的数组res
思路1： 最基础的做法，原模原样的构建一个滑窗，比较出最大值，放入res中
代码1：
    异常判断
        arr -> 空
        w -> 小于1
        w < arr.len
    初始化
        窗口左右边界
        res数组
        放入位置idx
    计算
        从R = w - 1 开始到 R = len-1结束
            第一个值初始化成max
            依次比较窗口内的每一个值
            获得最大值
            塞入结果数组
    返回

思路2：
    1）搞一个双端队列，持续记录当前窗口下的缩减最大值，把首位塞到结果数组中。
    2）更新策略，只要窗口右移，就取出新碰到的数字，和队列尾部记录的下标的数字不断比较，踢掉所有小于等于当前值得记录，放入新数字，同时检查窗口右移后，队列中是否有下标过期的数字，同样踢掉。
    3）该队列的含义为，在当前窗口左边界依次右移，致使窗口不断缩小的情况下，窗口内最大值会如何变化。
    4）踢掉小于等于值得意义是，因为右边界不会左移，所以只要右边出现一个相对大的数了，在左边界右移窗口缩小的过程中，中间的哪些小值永远没可能成为窗口内最大值。
代码：
    异常判断
        arr -> 空
        w -> 小于1
        w < arr.len
    初始化
        双端队列
    计算
        窗口的右边界从左外侧进入，直至碰到右壁
            更新双端队列
                更新双端队列右侧值
                放入该值
                更新双端队列左侧值
            在窗口全部进入的情况下，获取首位塞入结果数组
    返回
易错：
    注意双端队列中放的是下标
    剔除队列右侧的过程是while而非if
    注意边界不要卡错
*/


public class Code01_SlidingWindowMaxArray {

    public static int[] right(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        int N = arr.length;
        int[] res = new int[N - w + 1];
        int L = 0;
        int R = w - 1;
        int idx = 0;
        while (R < N) {
            int max = arr[L];
            for (int i = L+1; i <= L + w - 1; i++) {
                max = Math.max(max, arr[i]);
            }
            res[idx++] = max;
            L++;
            R++;
        }
        return res;
    }

    public static int[] getMaxWindow(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        LinkedList<Integer> qmax = new LinkedList<>();
        int idx = 0;
        int[] res = new int[arr.length - w + 1];
        for (int R = 0; R < arr.length; R++) {
            while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[R]) {
                qmax.pollLast();
            }
            qmax.addLast(R);
            if (qmax.peekFirst() < R - w + 1) {
                qmax.pollFirst();
            }
            if (R >= w - 1) {
                res[idx++] = arr[qmax.peekFirst()];
            }
        }
        return res;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int testTime = 100000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int w = (int) (Math.random() * (arr.length + 1));
            int[] ans1 = getMaxWindow(arr, w);
            int[] ans2 = right(arr, w);
            if (!isEqual(ans1, ans2)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }

}
