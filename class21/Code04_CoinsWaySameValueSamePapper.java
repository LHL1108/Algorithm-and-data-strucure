package class21;
/*
要求: arr是一个货币数组，每个值代表一张货币，认为同样面值的货币都是完全一样的，求组成aim金额的方法数
      如，arr = {1,2,1,1,2,1,2}, aim = 4, 共有1111、112、22三种方法，返回3

思路：这道题和无限张数的唯一区别就是对张数进行了限制，所以在迭代时除了张数*金额不能超过剩余金额，还要要求
      使用张数不能超过总张数，所以我们需要知道每张面值的张数，在前期先处理arr，将其转化为两个同样长度的面值和张数数组即可
      具体方法为先用哈希表统计张数，在依次取出entry分别放入两个数组中

代码：
    构建Info类
        初始化参数
            面值
            张数
        初始化方法
    获取Info方法
        初始化哈希表
        哈希表统计
        创建数组
        依次放入数组
        返回
    主方法
        异常判断
            arr null, 0len, 金额小于0
        调用递归
        返回
    递归函数
        basecase
            看完最后一张后
                剩余金额是否为0
                    是
                        1
                    否
                        0
        递归
            从0张开始，到金额超过限制或张数超过限制，进行尝试，所有方法数加和
                递归运行下一层函数，index+1,金额减去对应
        返回总方法数
*/

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Code04_CoinsWaySameValueSamePapper {

    public static class Info {
        int[] coins;
        int[] zhangs;
        public Info(int[] c, int[] n) {
            coins = c;
            zhangs = n;
        }
    }

    public static Info getInfo(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int item : arr) {
            if (!map.containsKey(arr)) {
                map.put(item, 1);
            } else {
                map.put(item, map.get(item) + 1);
            }
        }
        int[] cash = new int[arr.length];
        int[] nums = new int[arr.length];
        int idx = 0;
        for (Entry<Integer, Integer> e : map.entrySet()) {
            cash[idx] = e.getKey();
            nums[idx] = e.getValue();
            idx++;
        }
        Info info = new Info(cash, nums);
        return info;
    }


    public static int coinsWay(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        Info Info = getInfo(arr);
        int res = process(Info.coins, Info.zhangs, 0, aim);
        return res;
    }

    public static int process(int[] cash, int[] nums, int idx, int rest) {
        if (idx == cash.length) {
            if (rest == 0) {
                return 1;
            } else {
                return 0;
            }
        }
        int ways = 0;
        for (int zhang = 0; cash[idx]*zhang <= rest && zhang <= nums[idx]; zhang++) {
            ways += process(cash, nums, idx + 1,rest - (cash[idx] * zhang));
        }
        return ways;
    }












    public static int dp1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        Info info = getInfo(arr);
        int[] coins = info.coins;
        int[] zhangs = info.zhangs;
        int N = coins.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ways = 0;
                for (int zhang = 0; zhang * coins[index] <= rest && zhang <= zhangs[index]; zhang++) {
                    ways += dp[index + 1][rest - (zhang * coins[index])];
                }
                dp[index][rest] = ways;
            }
        }
        return dp[0][aim];
    }

    public static int dp2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        Info info = getInfo(arr);
        int[] coins = info.coins;
        int[] zhangs = info.zhangs;
        int N = coins.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (rest - coins[index] >= 0) {
                    dp[index][rest] += dp[index][rest - coins[index]];
                }
                if (rest - coins[index] * (zhangs[index] + 1) >= 0) {
                    dp[index][rest] -= dp[index + 1][rest - coins[index] * (zhangs[index] + 1)];
                }
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
        int maxLen = 10;
        int maxValue = 20;
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
