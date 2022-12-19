package class19;
/*
要求： 规定1和A对应、2和B对应...26和Z对应，那么"111"可以转化的字母序列有三种，AAA、AK、KA
      给定一串数字字符串，求可以有多少种转换方法
思路： 把原问题转化为从当前位置开始有多少种转换方法，增加参数当前位置i
      basecase1，当前位置数字是0，则说明无法转换返回0
      basecase2，当前位置在末尾，说明转换成功，返回1，代表成功试出1种方法
      try1，单转当前字符，直接跳到下个位置
      try2,转2个字符，前提是有下一个且数字之和小于等于26，跳到下下个
代码：
    主函数
        异常判断
            空
            长度为0
            返回0
        运行迭代函数

    迭代函数
        输入
            字符数组，当前位置
        base case
            末尾位置
            当前为0
        尝试方法
            单转当前
                跳位
            转两个
                判断
                跳位
        返回
*/


public class Code02_ConvertToLetterString {

    public static int number(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        return process(str.toCharArray(), 0);
    }

    public static int process(char[] s, int i) {
        if (i == s.length) {
            return 1;
        }
        if (s[i] == '0') {
            return 0;
        }

        int p1 = process(s, i + 1);
        int p2 = 0;
        if (i+1 < s.length && ((s[i] - '0') * 10 + (s[i+1] - '0') <= 26)) {
            p2 = process(s, i + 2);
        }
        int res = p1 + p2;
        return res;
    }

    public static int dp(String str) {
        char[] s = str.toCharArray();
        int N = s.length;
        int[] dp = new int[N + 1];
        dp[N] = 1;
        for (int i = N-1; i >= 0 ;i--) {
            if (s[i] == '0') {
                dp[i] = 0;
            } else {
                int p1 = dp[i+1];
                int p2 = 0;
                if (i+1 < N && ((s[i] - '0') * 10 + (s[i+1] - '0') <= 26)) {
                    p2 = process(s, i + 2);
                }
                dp[i] = p1 + p2;
            }
        }
        return dp[0];
    }



    public static void main(String[] args) {
        // expect 54
        System.out.println(number("7210231231232031203123"));
        System.out.println(dp("7210231231232031203123"));
    }

}