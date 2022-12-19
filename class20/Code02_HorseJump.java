package class20;
/*
要求：在一个9*10大小的棋盘上，起始点(0,0),目的地为(a,b)，步数为k,求有几种跳法
思路：将该问题转换为从(a1,b1)跳rest步至(a2,b2)，有几种可能的暴力递归问题，basecase是rest为零，判断位置是否正确
代码：
    主函数
        直接运行(0,0)
    递归函数
        异常判断
            越界的4种情况
        base case
            在
            不在
        递归
            八种情况加和
思路：
    暴力递归有三个参数，考虑三阶dp，根据basecase第0层只有一个点是1，剩余全是0，根据递归函数，有了下面一层的全部，
    就一定可以填好上面，所以从下往上依次填

代码思路：
    创建数组
        a,b,k+1
    填好第0层
        一个点是1，其余初始值
    填好其他位置
        从第一层开始依次填
            获取每个位置的值，为了
    获取结果
易错点： 注意边界处理
*/

public class Code02_HorseJump {

    public static int jump(int a, int b, int k) {
        return process(0, 0, k, a, b);
    }

    public static int process(int i, int j, int rest, int a, int b) {
        if (i < 0 || i > 9 || j < 0 || j > 8) {
            return 0;
        }
        if (rest == 0) {
            return (i == a) && (j == b) ? 1 : 0;
        }
        int cnt = process(i-1, j+2, rest - 1, a, b);
        cnt += process(i+1, j+2, rest - 1, a, b);
        cnt += process(i-1, j-2, rest - 1, a, b);
        cnt += process(i+1, j-2, rest - 1, a, b);
        cnt += process(i-2, j+1, rest - 1, a, b);
        cnt += process(i-2, j-1, rest - 1, a, b);
        cnt += process(i+2, j+1, rest - 1, a, b);
        cnt += process(i+2, j-1, rest - 1, a, b);
        return cnt;

    }

    public static int dp(int a, int b, int k) {
        int[][][] dp = new int[9][10][k+1];
        dp[a][b][0] = 1;
        for (int rest = 1; rest <= k; rest++) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 10; j++) {
                    dp[i][j][rest] =  pick(dp, i-1, j+2, rest - 1);
                    dp[i][j][rest] += pick(dp, i+1, j+2, rest - 1);
                    dp[i][j][rest] += pick(dp, i-1, j-2, rest - 1);
                    dp[i][j][rest] += pick(dp, i+1, j-2, rest - 1);
                    dp[i][j][rest] += pick(dp, i-2, j+1, rest - 1);
                    dp[i][j][rest] += pick(dp, i-2, j-1, rest - 1);
                    dp[i][j][rest] += pick(dp, i+2, j+1, rest - 1);
                    dp[i][j][rest] += pick(dp, i+2, j-1, rest - 1);
                }
            }
        }
        return dp[0][0][k];
    }

    public static int pick(int[][][] dp, int i, int j, int rest) {
        if (i < 0 || i > 8 || j < 0 || j > 9) {
            return 0;
        } else {
            return dp[i][j][rest];
        }
    }



    public static void main(String[] args) {
        int x = 7;
        int y = 7;
        int step = 10;
        // except 515813
        System.out.println(jump(x, y, step));
        System.out.println(dp(x, y, step));
//        System.out.println(jump(x, y, step));
    }
}
