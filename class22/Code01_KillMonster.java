package class22;
/*
要求： 怪兽有N滴血，每次打击造成[0, M]点伤害，求K次打击后怪兽被打死的概率
思路： 打死的概率为打死的方法数/总方法数，总方法数为M+1的K次方，所以把该问题转化成有多少种打死的方法
       因为每次可砍的刀数及血量都在减少，问题的规模在逐渐缩小，所以考虑暴力递归方法，basecase为剩余0刀的时候，血量是否在0以下
代码：
    主方法
        求能打死的方法数
        求总方法数
        计算
        返回
    迭代函数
        basecase
            剩余0刀的情况下
                血量<=0
                    返回1
                血量>0
                    返回0
        迭代
            刀数-1，血量依次-[0, M]
        求和
        返回

思路2：有两个变量的暴力递归，考虑动态规划方法，维度[N+1,K+1], basecase 血量=0，后续方法数可以直接算，M+1的K次方
      再推第二行，每个位置用for循环叠加一遍，记得不要搞错参数
代码：
    初始化二维数组
        [N+1,K+1]
    填写basecase
        basecase 血量=0 直接算
    填写剩余
        对于每个位置，将需要的中间值求和
    计算
        [N,K] / (M+1)的K次方
    返回
*/

public class Code01_KillMonster {

    public static double right(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        return (double) process(N, M, K) / (double) Math.pow(M + 1, K);
    }

    public static int process(int N, int M, int K) {
        if (K == 0) {
            return N <= 0 ? 1 : 0;
        }
        int res = 0;
        for (int i = 0; i <= M; i++) {
            res += process(N-i, M, K-1);
        }
        return res;
    }


    public static double dp1(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        double[][] dp = new double[N+1][K+1];
        dp[0][0] = 0;
        for (int k = 1; k <= K; k++) {
            dp[0][k] = Math.pow((M+1), k);
        }

        for (int n = 1; n <= N; n++) {
            for(int k = 1; k <= K; k++) {
                int ways = 0;
                for (int i = 0; i <= M; i++) {
                    if (n > i) {
                        ways += dp[n-i][k-1];
                    } else {
                        ways += Math.pow((M+1), k-1);
                    }

                }
                dp[n][k] = ways;
            }
        }
        return (double) dp[N][K] / (double) Math.pow((M+1), K);
    }

    public static double dp2(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        double[][] dp = new double[K+1][N+1];
        dp[0][0] = 1;
        for (int k = 1; k <= K; k++) {
            dp[k][0] = Math.pow(M + 1, k);
            for (int n = 1; n <= N; n++) {
                dp[k][n] = dp[k][n-1] + dp[k-1][n];
                if (n - M -1 >= 0) {
                    dp[k][n] -= dp[k-1][n-M-1];
                } else {
                    dp[k][n] -= Math.pow(M + 1, k-1);
                }
            }
        }
        double res = (double) dp[K][N] / (double) Math.pow(M + 1, K);
        return res;
    }





    public static void main(String[] args) {
        int NMax = 10;
        int MMax = 10;
        int KMax = 10;
        int testTime = 200;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * NMax);
            int M = (int) (Math.random() * MMax);
            int K = (int) (Math.random() * KMax);
            double ans1 = right(N, M, K);
            double ans2 = dp1(N, M, K);
            double ans3 = dp2(N, M, K);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }

}
