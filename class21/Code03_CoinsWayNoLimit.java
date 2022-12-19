package class21;
/*
要求：arr是一个货币的面值数组，每一个面值都可以任意选择张数，求可以正好组成rest金额的方法数
思路： 这道题和上一道每个货币只有一张是类似的，都是从左往右的尝试模型，区别在于此题每个货币可以选择不同情况的张数，从0至使金额达到上限的张数，每种情况都会进一步衍生，
      所以这一题属于递归中有迭代的情况，针对此情况，列个for循环即可，要与不要也可以融入for循环中
代码：
    主函数
        异常判断
            arr 空，0长，aim < 0
        调用递归函数

    递归函数
        basecase
            当所有货币都看过
                rest为0
                    找到1中方法
                rest不为0
                    没找到
        递归
            从0张到不超过金额上限的可能张数
                对每种情况下递归结果求和，注意位置只变化1，金额变化面值*数量
        返回


思路：动态规划，因为暴力递归版本只有两个可变参数所以考虑动态规划，由递归函数确定从下往上填，且只依赖正下方和左下方的几个值
      由basecase进行初始化，获得最下面一行值，上推即可。
代码：
    创建dp二维表
    根据basecase初始化
    依次填好
        由下向上
            由左向右
                填写，找满足条件的左下方的值
    返回右上角值
*/

public class Code03_CoinsWayNoLimit {

    public static int coinsWay(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        return process(arr, 0, aim);
    }

    public static int process(int[] arr, int idx , int rest) {
        if (idx == arr.length) {
            if (rest == 0) {
                return 1;
            } else {
                return 0;
            }
        }

        int ways = 0;
        for (int nums = 0; nums * arr[idx] <= rest; nums++) {
            ways += process(arr, idx+1, rest - (nums * arr[idx]));
        }

        return ways;
    }

    //dp1
    public static int dp1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N+1][aim+1];

        dp[N][0] = 1;
        for (int i = N-1; i >= 0; i--) {
            for (int j = 0; j <= aim; j++) {
                for (int nums = 0; nums * arr[i] <= j; nums++) {
                    dp[i][j] += dp[i+1][j - (nums * arr[i])];
                }
            }
        }
        return dp[0][aim];
    }



    public static int dp2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (rest - arr[index] >= 0) {
                    dp[index][rest] += dp[index][rest - arr[index]];
                }
            }
        }
        return dp[0][aim];
    }

    // 为了测试
    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        boolean[] has = new boolean[maxValue + 1];
        for (int i = 0; i < N; i++) {
            do {
                arr[i] = (int) (Math.random() * maxValue) + 1;
            } while (has[arr[i]]);
            has[arr[i]] = true;
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
        int maxLen = 10;
        int maxValue = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = coinsWay(arr, aim);
            int ans2 = dp1(arr, aim);
            int ans3 = dp2(arr, aim);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
