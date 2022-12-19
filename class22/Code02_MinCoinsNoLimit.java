package class22;
/*
要求： arr是一个面值数组，每个面值无限张，其中的值都是不重复的正数，再给定一个aim，求arr中获取最少几张货币可以组成aim
思路1：把原问题转化为我现在已经确定一部分面值了，求剩下的钱数用剩下的面值最少多少张可以搞定，问题的规模被缩小，暴力递归
      但是对于每个面值而言，又有遍历尝试，因为我可以拿0张、1张、2张，一直到超过剩余钱数，然后比较，本轮我拿1张的
      总张数和我拿两张的总张数等等谁最小，取最小的作为结果。
代码1：
    主函数
        调用递归函数
            初始观察面值为0
    递归函数
        basecase
            如果都看完了
                剩余钱数是0，就返回0，否则搞不定返回系统最大值
        如果剩余钱数是负数
            搞不定，返回系统最大值
        如果不是负数
            初始化结果为最大值
            张数从0张尝试，到花光所有钱为止
                剩余钱数减少，观察面值后移，递归计算
                如果后面能搞定
                    算出总张数
                    和当前方案进行比较取小
    返回

思路2: dp方法，有暴力递归观察得到，如果以idx为行的话，那么每一个值都依赖下一行的某些值，因此从下往上推。
       根据basecase完成表格初始化，即当idx为len的时候，除了剩余钱数为0填0，剩下都搞不定填系统最大值
       往上填的过程照搬后略修改即可
代码2：
    创建dp表
        [N+1, aim+1]
    初始化
        第N行中
            0位置是0
            剩余位置系统最大值
    填剩下值
        倒数第二行往上遍历
            从左往右遍历
                初始化结果为最大值
                计算每种方法数
                取最小
                填写
    返回
        [0, aim]

思路3：因为dp中用到了的遍历，所以考虑复用，观察可得，复用左边的值，但需要做些调整，即左边的最小值要+1，因为已用张数是不同的，
     然后和正下方的元素比较。但有两种情况例外，不进行比较，一种是左边越界了，一种是左边的值搞不定，此时+1会导致数值异常，所以直接采用正下方的值
代码3：
    异常情况
        <1 返回0
    创建
    初始化
    填写
        如果左边有并且能搞定
            比较左边的最小值+1，和正下方的值，取小
        否则
            直接采用正下方的值
    返回
        [0, aim]
*/

public class Code02_MinCoinsNoLimit {

    public static int minCoins(int[] arr, int aim) {
        return process(arr, aim, 0);
    }

    public static int process(int[] arr, int rest, int idx) {
        if (idx == arr.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        } else {
            if (rest < 0) {
                return Integer.MAX_VALUE;
            } else {
                int res = Integer.MAX_VALUE;
                for (int zhang = 0; zhang * arr[idx] <= rest; zhang++) {
                    int nums = process(arr, rest - (zhang * arr[idx]), idx+1);
                    if (nums != Integer.MAX_VALUE) {
                        res = Math.min(res, zhang + nums);
                    }
                }
                return res;
            }
        }
    }


    public static int dp1(int[] arr, int aim) {
        int N = arr.length;
        int[][] dp = new int[N+1][aim+1];
        dp[N][0] = 0;
        for (int j = 1; j <= aim; j++) {
            dp[N][j] = Integer.MAX_VALUE;
        }

        for (int i = N-1; i >= 0; i--) {
            for (int j = 0; j <= aim; j++) {
                int res = Integer.MAX_VALUE;
                for (int zhang = 0; zhang * arr[i] <= j; zhang++) {
                    int nums = dp[i+1][j - (zhang * arr[i])];
                    if (nums != Integer.MAX_VALUE) {
                        res = Math.min(res, zhang + nums);
                    }
                }
                dp[i][j] = res;
            }
        }
        return dp[0][aim];
    }

    public static int dp2(int[] arr, int aim) {
        int N = arr.length;
        int[][] dp = new int[N+1][aim+1];
        dp[N][0] = 0;
        for (int j = 1; j <= aim; j++) {
            dp[N][j] = Integer.MAX_VALUE;
        }

        for (int i = N-1; i >= 0; i--) {
            for (int j = 0; j <= aim; j++) {
                if (j - arr[i] >= 0 && dp[i][j-arr[i]] != Integer.MAX_VALUE) {
                    dp[i][j] = Math.min(dp[i][j-arr[i]] + 1, dp[i+1][j]);
                } else {
                    dp[i][j] = dp[i+1][j];
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
        int maxLen = 20;
        int maxValue = 30;
        int testTime = 300000;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * maxLen);
            int[] arr = randomArray(N, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = minCoins(arr, aim);
            int ans2 = dp1(arr, aim);
            int ans3 = dp2(arr, aim);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("功能测试结束");
    }

}
