package class26;
/*
要求： 实现一个斐波那契数列，在N分别为12345时，值为 1 2 3 5 8 ...
思路： 递归版本，规定好1,2的初始值后边只用加N-1， N-2即可
      迭代版本，规定好p1,p2位置，用tmp保存p2原始值，p2值累加的形式，注意迭代次数即可
      直接计算版本，

思路3：公式法直接计算，时间复杂度O(logN) |F(n), F(n-1)| = |F(2), F(1)| * T^n-2
        带入N = 3，再左移，解出T，再算出T^n-2的结果ABCD, 那么F(N) = F(2)A + F(1)C
易错： 注意单位矩阵的构建方式，不是两个for循环，那样会给所有位置置1，正确的方法是一个for循环，坐标[i][i]置1
代码3：
    basecase1
        N = 1
    basecase2
        N = 2
    N >= 3
        计算T^n-2
        计算结果
            F(N) = F(2)A + F(1)C
    矩阵次幂函数
        构建单位矩阵
        构建测试矩阵
        算出结果矩阵
            解构次幂
            判断10
            加入或不加到结果
            计算下一轮
*/


public class Code02_FibonacciProblem {
    public static int f1(int N) {
        if (N < 1) {
            return 0;
        }
        if (N == 1) {
            return 1;
        } else if (N == 2) {
            return 2;
        } else {
            return f1(N - 1) + f1(N - 2);
        }
    }

    public static int f2(int N) {
        if (N < 1) {
            return 0;
        }
        if (N == 1) {
            return 1;
        } else if (N == 2) {
            return 2;
        } else {
            int p1 = 1;
            int p2 = 2;
            int tmp = 0;
            for (int i = 0; i < N - 2; i++) {
                tmp = p2;
                p2 += p1;
                p1 = tmp;
            }
            return p2;
        }

    }

    public static int f3(int N) {
        if (N < 1) {
            return 0;
        }
        if (N == 1) {
            return 1;
        } else if (N == 2) {
            return 2;
        } else {
            int res = 0;
            int[][] T = {{1, 1},{1, 0}};
            int[][] Tm = Tk(T, N-2);
            int A = Tm[0][0];
            int B = Tm[0][1];
            int C = Tm[1][0];
            int D = Tm[1][1];
            res = f3(2) * A  +f3(1) * C;
            return res;
        }
    }

    public static int[][] Tk(int[][] T, int k) {
        int[][] basic = new int[T.length][T[0].length];
        for (int i = 0; i < T.length; i++) {
                basic[i][i] = 1;
        }
        int[][] t = T;
        int[][] res = basic;

        for(; k != 0; k >>= 1) {
            if ((k & 1) == 1) {
                res = multiple(res, t);
            }
            t = multiple(t, t);
        }
        return res;
    }

    public static int[][] multiple(int[][] t1, int[][] t2) {
        int[][] res = new int[t1.length][t2[0].length];
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
        int N = 10;
        for (int i = 1; i < N; i++) {
            int res1 = f1(i);
            int res2 = f2(i);
            int res3 = f3(i);
            System.out.println(res3);
        }

    }
}
