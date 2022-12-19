package class20;
/*
要求： 返回一个仅由小写字母组成的字符串的最长回文子序列长度，如: "abc123aca" 返回3 , "abbeddc"返回5 , "bbbab"返回4
      子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列
思路1： 将在当前串寻找子序列长度转化为在它某种情况的子串(包括它本身)中寻找，以此逐渐缩短字符串长度，穷举所有情况，比较得出最大值
代码1：
    主函数
        异常判断
            null
            长度为0
        转换成char[]
        调用递归函数

    递归函数
        basecase
            长度为1
                返回1
            长度为2
                根据是否相同返回1或2
        递归部分
            列情况
                最长子串在中间
                最长子串在左半部分
                最长子串在右半部分
                最长子串在它本身
                    前提是两边相等
                        等就是2+中间
                        不等就是0
            比较选出最大值
            返回

思路2(dp版本):
    既然方法1是暴力递归，而且可变参数是2个，那么我们可以考虑用动态规划的表格方法来解，先填basecase，即两个对角线
    在填其他部分，注意填写的顺序即可，通过与递归函数参数对应的表格的位置的值来获取答案
代码2:
    异常情况
        0
        空
    初始化表格
    填写basecase
        先填右下角
        再填每一行的两个
    填写其他部分
        从下往上且从左往右
            比较左与右
            如果左右相等
                再比较其与2+中间
    返回目标位置值
*/


public class Code01_PalindromeSubsequence {
    public static int lpsl1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] c = s.toCharArray();
        return process(c, 0, c.length - 1);
    }

    public static int process(char[] c, int L, int R) {
        if (L == R) {
            return 1;
        } else if (L + 1 == R) {
            return c[L] == c[R] ? 2 : 1;
        } else {
            int p1 = process(c, L, R-1);
            int p2 = process(c, L+1, R-1);
            int p3 = process(c, L+1, R);
            int p4 = c[L] == c[R] ? 2 + process(c, L+1, R-1) : 0;
            int ans = Math.max(Math.max(p1, p2), Math.max(p3, p4));
            return ans;
        }
    }

    public static int lpsl2(String s) {
        if (s.length() == 0 || s == null) {
            return 0;
        }

        char[] c = s.toCharArray();
        int N = s.length();
        int[][] dp = new int[N][N];

        dp[N-1][N-1] = 1;
        for(int i = 0; i < N-1; i++) {
            dp[i][i] = 1;
            dp[i][i+1] = c[i] == c[i+1] ? 2 : 1;
        }
        for(int i = N-3; i >= 0; i--) {
            for (int j = i+2; j <= N-1; j++) {
                dp[i][j] = Math.max(dp[i][j-1], dp[i+1][j]);
                if (c[i] == c[j]) {
                    dp[i][j] = Math.max(dp[i][j], 2 + dp[i+1][j-1]);
                }
            }
        }

        return dp[0][N-1];
    }
}
