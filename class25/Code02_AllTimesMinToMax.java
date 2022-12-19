package class25;
import java.util.Stack;

/*
要求： 给定一个只包含正数的数组arr，arr中的任何一个子数组都可以算出 (sub累加和) * (sub中的最小值)是什么，那么所有子数组中，这个值最大是多少
思路： 不管这个最大值是多少，这个子数组的最小值一定是arr数组中的某个值，又因为arr是个正数数组，范围越大累加和越大，所以这个最小值的答案一定是以它为最小值的前提下能扩的最大范围。
      所以只要依次尝试每一个值，并根据这个值能扩的最大范围，更新出全局最大值，即使答案。
      第二个技巧是，如何快速求出范围内的和，用累加和数组的特定位置直接相减
      第三个技巧是，这里等于也同样踢，因为遇到相同可以放到后面一起算，因为有多个最小值的情况下，仍然符合以该数值为最小值的题意，可以继续再往后拓
易错： 要时刻记清楚栈中放的是位置，因此乘的是arr[p]而不是p，
代码：
    异常
        空
        零
    初始化
        累加和数组
    处理
        遍历每一个数作为最小值
            求该情况下的最大范围
            求该范围的和
            求该情况下的结果
            更新最大值
    返回
*/

public class Code02_AllTimesMinToMax {

    public static int max2(int[] arr) {
        if (arr == null && arr.length == 0) {
            return 0;
        }
        Stack<Integer> stack = new Stack<Integer>();
        int max = Integer.MIN_VALUE;
        int N = arr.length;
        int[] sum = new int[N];
        sum[0] = arr[0];
        for (int i = 1; i < N; i++) {
            sum[i] = sum[i-1] + arr[i];
        }

        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                int p = stack.pop();
                int curValue = arr[p] * (stack.isEmpty() ? sum[i-1] : sum[i-1] - sum[stack.peek()]);
                max = Math.max(max, curValue);
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int p = stack.pop();
            int curValue = arr[p] * (stack.isEmpty() ? sum[N-1] : sum[N-1] - sum[stack.peek()]);
            max = Math.max(max, curValue);
        }

        return max;
    }











    // for test

    public static int max1(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int minNum = Integer.MAX_VALUE;
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += arr[k];
                    minNum = Math.min(minNum, arr[k]);
                }
                max = Math.max(max, minNum * sum);
            }
        }
        return max;
    }


    public static int[] gerenareRondomArray() {
        int[] arr = new int[(int) (Math.random() * 20) + 10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 101);
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTimes = 20000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = gerenareRondomArray();
            if (max1(arr) != max2(arr)) {
                System.out.println("FUCK!");
                break;
            }
        }
        System.out.println("test finish");
    }

}
