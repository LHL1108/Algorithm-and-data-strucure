package class22;
/*
要求：将一个数字拆成若干个，后面的不能比前面的小，求有几种拆法
思路：因为此题时动态规划，所以一定可以递归，问题的规模在缩小，什么在缩小，剩余数字在缩小，所以是再剩余数字的情况下，且上一轮已经拆了pre，返回拆完剩下的有多少种方法数
      basecase有两种，一种是拆到不剩数字，返回1种，另外是剩余数字小于当前已拆数字，说明已无法解决，返回0
     递归过程中更新已拆数字和待拆数字即可
代码：
    主函数
        待拆数是0
            返回0
        是1
            返回1
        其他
            调用递归

    递归函数
        basecase
            剩余数字是0
                返回1
            剩余数字小于已拆数字
                返回0
        递归
            更新参数后调用递归
            方法数求和
        返回
思路2： dp方法，基本照搬，改动1处，一是多了一个拆分数和剩余数相等的情况，填1，填值照搬递归，把递归改为取值即可
代码2：
    基本情况
        0
        1
    创建dp表[n+1, n+1]
    初始化
        rest = 0
        pre = rest
    填剩余值
        从下往上
        从左往右
        照搬递归，改为取值
    返回[1, n]
思路3： 观察得，每个值可有下个值加左边的某个值得到，左边的值与该位置的横纵坐标有关
*/

public class Code03_SplitNumber {


    public static int ways(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return process(1, n);
    }

    public static int process(int pre, int rest) {
        if (rest == 0) {
            return 1;
        }
        if (rest == pre) {
            return 1;
        }
        if (rest < pre) {
            return 0;
        }
        int ways = 0;
        for (int i = pre; i <= rest; i++) {
            ways += process(i, rest - i);
        }
        return ways;
    }

    public static int dp1(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        int[][] dp = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;
            dp[i][i] = 1;
        }
        for (int i = n-1; i >= 1; i--) {
            for (int j = i+1; j <= n; j++) {
                int ways = 0;
                for (int first = i; first <= j; first++) {
                    ways += dp[first][j-first];
                }
                dp[i][j] = ways;
            }
        }
        return dp[1][n];
    }


    public static int dp2(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        int[][] dp = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;
            dp[i][i] = 1;
        }
        for (int i = n-1; i >= 1; i--) {
            for (int j = i+1; j <= n; j++) {
                dp[i][j] = dp[i+1][j];
                dp[i][j] += dp[i][j-i];
            }
        }
        return dp[1][n];
    }







    public static void main(String[] args) {
        int test = 39;
        System.out.println(ways(test));
        System.out.println(dp1(test));
        System.out.println(dp2(test));
    }

}
