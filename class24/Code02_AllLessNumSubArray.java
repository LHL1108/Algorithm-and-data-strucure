package class24;
/*
要求： 求在数组arr当中有多少个符合窗口内最大值与最小值的差小于等于sum的连续子数组
思路1： 遍历出所有的组合，判断其最大最小值，计算差值并进行判断，更新进结果中
代码1：
     异常判断
        数组为空
        长度为0
        sum负数
     计算
        遍历出所有的子数组
            左边界从左至右
            右边界从左边界至右
        更新每种情况下的最小最大值
            初始化为窗口最左侧值
            向右遍历并比较更新
        计算差值并比较
            计算差值
            符合条件结果+1
     返回
思路2：
    把当前问题分成，第一个元素作为起始的子数组有多少个满足条件的+第2个元素作为起始的子数组有多少个满足条件的+...
    用双端队列计算出窗口内最大值最小值
    更新出当前左边界下的符合条件的最右右边界，因为一旦中间某个数不符合，数再多只会更不可能
    根据边界值的距离计算出当前左边界下的数组数
代码2：
    异常判断
        arr为空
        arr长度为0
        sum < 0
    计算
        对于每个左边界
            推进最大右边界
                更新双端队列
                判断
                    继续更新
                    跳出计算
            累加至结果中
    返回
易错：滑窗内+的是位置下标而不是值
*/
import java.util.LinkedList;

public class Code02_AllLessNumSubArray {

    public static int right(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum < 0) {
            return 0;
        }
        int cnt = 0;
        int N = arr.length;
        for (int L = 0; L < N; L++) {
            for (int R = L; R < N; R++) {
                int max = arr[L];
                int min = arr[L];
                for (int i = L + 1; i <= R; i++) {
                    max = Math.max(arr[i], max);
                    min = Math.min(arr[i], min);;
                }
                if (max - min <= sum) {
                    cnt += 1;
                }
            }
        }
        return cnt;
    }



    public static int num(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum < 0) {
            return 0;
        }
        int cnt = 0;
        int N = arr.length;
        int R = 0;
        LinkedList<Integer> qmax = new LinkedList<>();
        LinkedList<Integer> qmin = new LinkedList<>();
        for(int L = 0; L < N; L++) {
            while (R < N) {
                while(!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[R]) {
                    qmax.pollLast();
                }
                qmax.addLast(R);
                while(!qmin.isEmpty() && arr[qmin.peekLast()] >= arr[R]) {
                    qmin.pollLast();
                }
                qmin.addLast(R);
                if (arr[qmax.peekFirst()] - arr[qmin.peekFirst()] > sum) {
                    break;
                } else {
                    R++;
                }
            }
            cnt += R - L;
            if (qmax.peekFirst() == L) {
                qmax.pollFirst();
            }
            if (qmin.peekFirst() == L) {
                qmin.pollFirst();
            }
        }
        return cnt;
    }

    // for test
    public static int[] generateRandomArray(int maxLen, int maxValue) {
        int len = (int) (Math.random() * (maxLen + 1));
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int maxLen = 100;
        int maxValue = 200;
        int testTime = 1000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxLen, maxValue);
            int sum = (int) (Math.random() * (maxValue + 1));
            int ans1 = right(arr, sum);
            int ans2 = num(arr, sum);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(sum);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");

    }

}
