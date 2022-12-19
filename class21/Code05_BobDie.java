package class21;
/*
要求： 在一个N*M的网格上，醉汉BOB在(row, col)位置上，一共要迈出k步，每步都会等概率向上下左右的一个方向上走
      一个单位，只要离开网格，就会死亡，求BOB最后还活着的概率

思路： 生存概率等于活着的方法数/总方法数，总方法数即4的K次方，活着的方法数即依次减少步数，递归函数，basecase为2种，一种收敛于走到外面，这是方法数是0
      另一种收敛于剩余步数为0且还在棋盘内，这时获得方法数1

代码：

    主函数
        返回 递归函数/总方法数

    递归函数
        basecase
            走到外面
                返回0
            剩余步数为0
                返回1
        递归
            上下左右
                位置变更
                步数-1
        求和
        返回


思路2： 转写dp方法，首先考虑变量，一共三个，row, col, k,所以填一个立方体，初始化为k=0的所有，把走到外面的情况并在下面
代码2：
主函数
    创建三层立方体
    初始化
    填剩下
    取值
    计算
    返回
*/
public class Code05_BobDie {

    public static double livePosibility1(int row, int col, int k, int N, int M) {
        return (double) process(row, col, k, N, M) / Math.pow(4, k);
    }

    public static int process(int row, int col, int k, int N, int M) {
        if (row < 0 || row >= N || col < 0 || col >= M) {
            return 0;
        }
        if (k == 0) {
            return 1;
        }
        int up = process(row-1, col, k-1, N, M);
        int down = process(row+1, col, k-1, N, M);
        int left = process(row, col-1, k-1, N, M);
        int right = process(row, col+1, k-1, N, M);
        return up + down + left + right;
    }


    public static double livePosibility2(int row, int col, int k, int N, int M) {
        long[][][] dp = new long[N][M][k+1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dp[i][j][0] = 1;
            }
        }
        for (int rest = 1; rest <= k; rest++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    dp[i][j][rest] = pick(dp, N, M, i-1, j, rest-1);
                    dp[i][j][rest] += pick(dp, N, M, i+1, j, rest-1);
                    dp[i][j][rest] += pick(dp, N, M, i, j-1, rest-1);
                    dp[i][j][rest] += pick(dp, N, M, i, j+1, rest-1);
                }
            }
        }

        return (double) dp[row][col][k] / Math.pow(4, k);
    }



    public static long pick(long[][][] dp, int N, int M, int r, int c, int rest) {
        if (r < 0 || r == N || c < 0 || c == M) {
            return 0;
        }
        return dp[r][c][rest];
    }



    public static void main(String[] args) {
        System.out.println(livePosibility1(6, 6, 10, 50, 50));
        System.out.println(livePosibility2(6, 6, 10, 50, 50));
    }

}
