package class26;
/*
要求： 给定一串N位数字，假设所有0的左边都是1，问有多少种可能
思路： 因为每个0左边都是1，所以最左只可能是1，第2位可能是1可能是0.结果是两者的和，
       第2位若是1的可能数，即为在N-1位数字中有多少种符合条件的可能数
       第2位若是0的可能数，即为在N-2位数字中有多少种符合条件的可能数
       所以F(N) = F(N-1) + F(N-2);
       此题为没有条件分支的斐波那契数列问题，运用公式|F(N), F(N-1)| = |F(2), F(1)| * T^(N-i)
       此题最多N-2，所以i = 2, 令N = 3，解出T = {{1, 1],{1, 0}}}
       写出矩阵的二进制次幂快速计算方法，即可解出本题Fn
代码：
    主函数
        异常
            N < 1  ->  1
        basecase
            N = 1  ->  1
            N = 2  ->  2
        其他
            T^N-i 解出结果
            根据公式F(N) = 2A + C
        返回
    求解次幂
        初始化
            构建单位矩阵
            构建测试矩阵
        计算结果
            次幂可拆解的情况下
                判断末尾是否为1
                    为1
                        称到结果中
                    不为1
                        不乘到结果中
                右移
        返回结果
*/

public class Code03_ZeroLeftOneStringNumber {
    public static int getNum1(int N) {
        if (N < 1) {
            return 0;
        } else if (N == 1) {
            return 1;
        } else if (N == 2) {
            return 2;
        } else {
            int[][] T = {{1, 1}, {1, 0}};
            int[][] Tm = Tk(T, N - 2);
            int res = 2 * Tm[0][0] + Tm[1][0];
            return res;
        }
    }

    public static int[][] Tk(int[][] T, int k) {
        int[][] basic = new int[T.length][T[0].length];
        for (int i = 0; i < T.length; i++) {
            basic[i][i] = 1;
        }
        int[][] t = T;
        int[][] ans = basic;
        for (; k != 0; k >>= 1) {
            if ((k & 1) == 1) {
               ans = multiple(ans, t);
            }
            t = multiple(t, t);
        }
        return ans;
    }

    public static int[][] multiple(int[][] t1, int[][] t2) {
        int[][] res =  new int[t1.length][t2[0].length];
        for (int i = 0; i < t1.length; i++) {
            for (int j = 0; j < t2[0].length; j++) {
                for (int k = 0; k < t2.length; k++) {
                    res[i][j] += t1[i][k] * t2[k][j];
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        for (int i = 1; i < 6; i++) {
            int n = getNum1(i);
            System.out.println(n);
        }
    }
}
