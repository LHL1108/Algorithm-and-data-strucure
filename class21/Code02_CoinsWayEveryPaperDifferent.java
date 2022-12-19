package class21;
/*
要求：有一个货币数组，值都为正数，即使面值相同的两张货币也认为是不同的，有一个正数aim，问有几种不同的组合方式
     例如, arr = {1, 1, 1},  aim = 2, 返回3
思路1: 暴力递归，将问题转化为从idx位置开始往后，凑齐rest金额有几种方法，这样idx逐渐后移，rest逐渐减少，问题规模被缩小。
       basecase是到了最后一张，能正好凑齐就找到了一种，否则就是0种，注意选择当前货币是有前提的，即钱够、
代码1：
    主函数
        调用递归函数

    递归函数
        basecase
            已经看到最后一张
                rest是否为0
                    是
                        返回1
                    否
                        返回0
        递归迭代
            在金额足够的情况下，要当前
                idx更新，rest更新
            不要当前
                idx更新
            总方法数为两种情况求和
        返回

思路2：动态规划，因为暴力递归可变参数为2个，考虑动态规划，注意边界，一个是从0-N，一个是从0-rest,根据basecase可知，从idx为N开始推
      由于idx行结果都为idx+1行的两个值求和，所以从下往上推，最后返回0，rest位置值
代码2：
    创建dp二维表
    根据basecase初始化
    从下往上
        从左往右填好每一行
            两个求和，注意要当前货币是有前提的，不满足前提按0来算
    返回0,rest位置值
*/

public class Code02_CoinsWayEveryPaperDifferent {

    public static int coinWays(int[] arr, int aim) {
        return process(arr, 0, aim);
    }

    public static int process(int[] arr, int idx, int rest) {
        if (idx == arr.length) {
            return rest == 0 ? 1 : 0;
        } else {
            int drop = process(arr, idx + 1, rest);
            int get = 0;
            if (rest >= arr[idx]) {
                get = process(arr, idx + 1, rest - arr[idx]);
            }
            return drop + get;
        }
    }

    public static int dp(int[] arr, int aim) {
        int N = arr.length;
        int[][] dp = new int[N+1][aim+1];

        dp[N][0] = 1;
        for (int i = N-1; i >= 0; i--) {
            for (int j = 0; j <= aim; j++) {
                dp[i][j] = dp[i+1][j] + ((j >= arr[i]) ? dp[i+1][j-arr[i]]: 0);
            }
        }

        return dp[0][aim];
    }





    // 为了测试
    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
        }
        return arr;
    }

    // 为了测试
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 为了测试
    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = coinWays(arr, aim);
            int ans2 = dp(arr, aim);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
