package class18;
/*
要求： 在一列从1到N的格子上，有一个站在start位置上的机器人，要去往aim位置，但是只能在不越界的情况下向左或向右走K步，问有几种走法。
思路：
    way1: 此问题的重复行为为：在1-N的格子上，在cur位置(可变)向aim位置走，剩rest步(可变)，basecase为rest=0,此时如果在aim上结果为1，否则结果为0
    way2: 用一张表把算过的结果记录下来，下次遇到重复的直接调
    way3: 根据规律把表上的所有值都提前算出来，不管是否为第一次遇到，都可以直接调
代码：
    主函数
        异常判断
        调用递归函数
        返回

    递归函数
        basecase
            如果rest==0
                是否在aim上返回0或1
        其他情况
            如果在最左
                返回右边位置递归
            如果在最右
                返回左边位置递归
            如果在中间
                返回左右递归的和
    */
public class Code01_RobotWalk {
    public static int ways1(int N, int start, int aim, int K) {
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 0) {
            return -1;
        }

        return process1(N, start, aim, K);
    }

    public static int process1(int N, int cur, int aim, int rest) {
        if (rest == 0) {
            return cur == aim ? 1 : 0;
        }

        if (cur == 1) {
            return process1(N, cur + 1, aim, rest - 1);
        } else if (cur == N) {
            return process1(N, cur - 1, aim, rest - 1);
        } else {
            return process1(N, cur + 1, aim, rest - 1) + process1(N, cur - 1, aim, rest - 1);
        }
    }


    public static int ways2(int N, int start, int aim, int K) {
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 0) {
            return -1;
        }

        int[][] dp = new int[N + 1][K + 1];
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < K+1; j++) {
                dp[i][j] = -1;
            }
        }

        return process2(N, start, aim, K, dp);
    }

    public static int process2(int N, int cur, int aim, int rest, int[][] dp) {
        if (dp[cur][rest] != -1) {
            return dp[cur][rest];
        } else {
            int ans = 0;
            if (rest == 0) {
                ans = cur == aim ? 1 : 0;
            } else {
                if (cur == 1) {
                    ans = process2(N, cur + 1, aim, rest - 1, dp);
                } else if (cur == N) {
                    ans = process2(N, cur - 1, aim, rest - 1, dp);
                } else {
                    ans = process2(N, cur + 1, aim, rest - 1, dp) + process2(N, cur - 1, aim, rest - 1, dp);
                }
            }
            dp[cur][rest] = ans;

            return ans;
        }
    }

    public static int ways3(int N, int start, int aim, int K) {
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 0) {
            return -1;
        }

        int[][] dp = new int[N + 1][K + 1];
        dp[aim][0] = 1;
        for (int j = 1; j < K+1; j++) {
            dp[1][j] = dp[2][j - 1];
            for(int i = 2; i <= N-1; i++) {
                dp[i][j] = dp[i + 1][j - 1] + dp[i - 1][j - 1];
            }
            dp[N][j] = dp[N - 1][j - 1];
        }

        return dp[start][K];
    }



    public static void main(String[] args) {
        // input 5,2,4,6 -> expext 13
        System.out.println(ways1(5,2,4,6));
        System.out.println(ways2(5,2,4,6));
        System.out.println(ways3(5,2,4,6));
    }
}
