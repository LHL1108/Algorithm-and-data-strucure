package class19;
/*
要求： 求两个字符串的最长公共子序列，例如，输入：text1 = "abcde", text2 = "ace"  输出：3 （"ace"）
思路： 将问题转换为如何求子串的最长公共子序列
代码:
    主函数
        异常判断
            分别两个为空、长度为0
        运行递归函数
    递归函数
        basecase
            两个都为0下标
                相等
                不相等
            其中一个为0下标
                相等
                不相等
        都不是0
            末尾不同
                a末尾是
                b末尾是
                都不是0
            末尾相同
                1+往前看
*/

public class Code04_LongestCommonSubsequence {
    public static int longestCommonSubsequence1(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
            return 0;
        }
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        return process(c1, c2, s1.length()-1, s2.length()-1);
    }

    public static int process(char[] c1, char[] c2, int idx1, int idx2) {
        if (idx1 == 0 && idx2 == 0) {
            if (c1[0] == c2[0]) {
                return 1;
            } else {
                return 0;
            }
        } else if (idx1 == 0) {
            if (c1[0] == c2[idx2]) {
                return 1;
            } else {
                return process(c1, c2, idx1, idx2 - 1);
            }
        } else if (idx2 == 0) {
            if (c2[0] == c1[idx1]) {
                return 1;
            } else {
                return process(c1, c2, idx1 - 1, idx2);
            }
        } else {
            int p1 = 0;
            int p2 = 0;
            int p3 = 0;
            int p4 = 0;
            if (c1[idx1] == c2[idx2]) {
                p1 = (1 + process(c1, c2, idx1 - 1, idx2 - 1 ));
            } else {
                p2 =  process(c1, c2, idx1 - 1, idx2 - 1 );
                p3 = process(c1, c2, idx1, idx2 - 1 );
                p4 = process(c1, c2, idx1 - 1, idx2);
            }
            int ans = Math.max(p1, Math.max(p2, Math.max(p3, p4)));
            return ans;
        }
    }



    public static int longestCommonSubsequence2(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
            return 0;
        }
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        int N = c1.length;
        int M = c2.length;
        int[][] dp = new int[N][M];

        dp[0][0] = c1[0] == c2[0] ? 1 : 0;
        for (int i = 1; i < M; i++) {
            dp[0][i] = c1[0] == c2[i] ? 1 : dp[0][i - 1];
        }
        for (int i = 1; i < N; i++) {
            dp[i][0] = c2[0] == c1[i] ? 1 : dp[i - 1][0];
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                int p1 = 0;
                int p2 = 0;
                int p3 = 0;
                int p4 = 0;
                if (c1[i] == c2[j]) {
                    p1 = 1 + dp[i-1][j-1];
                } else {
                    p2 =  dp[i-1][j-1];
                    p3 = dp[i][j-1];
                    p4 = dp[i-1][j];
                }
                dp[i][j] = Math.max(p1, Math.max(p2, Math.max(p3, p4)));
            }
        }
        return dp[N-1][M-1];
    }
}
