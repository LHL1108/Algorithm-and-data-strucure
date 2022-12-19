package class23;
/*
要求： 给定一个数组arr, 请将其分成两部分，使得两部分的累加和最接近，返回较小的那个集合的累加和
思路： 如果此题是一道动态规划题，那么一定可以暴力递归，那么一定有某些信息的规模在逐渐缩小，比如剩余的数，
      距离最终答案的距离，此外，使一个数组的累加和和另一个最接近，等效于使该累加和和平均值最接近。
      由此问题转化为，从arr的某个位置开始往后，最接近某值的累加和是多少，i的初始值是0，累加和的初始值是平均值
      basecase为到越界位置，累加和返回0
易错： 要此数时，当前及后续的最接近累加和为该数+后续最接近累加和，要记得加上当前数
代码：
    主函数
        异常情况
            null
            不够2个
        求平均值
        运行递归函数
        返回

    递归函数
        basecase
            越界
                返回0
        递归
            不要
                位置+1
                rest不变
                累加和为单纯后续
            要
                前提是可以要
                位置+1
                rest减少
                累加和为当前+后续
            返回要或不要中相对较大的那个，这样更接近

思路2：原路照搬，只要把对应函数改成dp表取值，根据原函数推测依赖关系确定填写顺序，看看basecase如何转化即可。
代码2：
    创建dp表
        [N+1, rest+1]
    填值
        初始化
            使用默认值即可
        剩余值
            从下往上
                从左往右
                    直接取对应值
                    比较填写
    返回
        [0, sum]

*/
public class Code01_SplitSumClosed {

    public static int right(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int item : arr) {
            sum += item;
        }
        return process(arr, 0, sum/2);
    }

    public static int process(int[] arr, int idx, int rest) {
        if (idx == arr.length) {
            return 0;
        }

        int p1 = process(arr, idx+1, rest);
        int p2 = 0;
        if (arr[idx] <= rest) {
            p2 = arr[idx] + process(arr, idx+1, rest - arr[idx]);
        }

        return Math.max(p1, p2);
    }


    public static int dp(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int n = arr.length;
        int sum = 0;
        for (int item : arr) {
            sum += item;
        }
        sum /= 2;
        int[][] dp = new int[n+1][sum+1];
        for (int i = n-1; i >= 0; i--) {
            for (int j = 0; j <= sum; j++) {
                int p1 = dp[i+1][j];
                int p2 = 0;
                if (arr[i] <= j) {
                    p2 = arr[i] + dp[i+1][j-arr[i]];
                }
                dp[i][j] = Math.max(p1, p2);
            }
        }

        return dp[0][sum];
    }




    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 50;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * maxLen);
            int[] arr = randomArray(len, maxValue);
            int ans1 = right(arr);
            int ans2 = dp(arr);
            if (ans1 != ans2) {
                printArray(arr);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }

}
