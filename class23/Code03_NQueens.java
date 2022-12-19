package class23;
/*
要求： 求解N皇后问题，即在一个N*N大小的棋盘上，摆放N个棋子，任何两个棋子不同行、不同列也不在同一条斜线上，求有多少种摆法
思路：由于是动态规划，规模缩小，即剩余行数缩小，另外可用行数在缩小，用record对用过的列进行记录。依次看每一行，在剩下的格子中进行选择，符合条件的，第一，不能和已有的同列，第二不能同斜线，即横纵坐标绝对差值不能相同
代码：
    主函数
        异常判断
            不能小于1
        创建record
        调用递归
            从0行开始
            传入空record
            棋盘大小为N
    递归函数
        basecase
            所有行已看完
                找到一种方法
        递归
            从上到下下遍历每一行
                从左到右遍历每一列
                    只要这列可以放
                        放
                            更新record
                            改变参数递归运行后续
    判断函数
        是否同列
        是否同斜线
        都不满足返回是
        否则返回否
*/

public class Code03_NQueens {

    public static int num1(int n) {
        if (n < 1) {
            return 0;
        }
        int[] record = new int[n];
        return process(0, record, n);
    }

    public static int process(int i, int[] record, int n) {
        if (i == n) {
            return 1;
        }
        int res = 0;
        for (int j = 0; j < n; j++) {
            if (isValid(record, i, j)) {
                record[i] = j;
                res += process(i+1, record, n);
            }
        }
        return res;
    }

    public static boolean isValid(int[] record, int i, int j) {
        for (int idx = 0; idx < i; idx++) {
            if (j == record[idx] || Math.abs(i - idx) == Math.abs(j - record[idx])) {
                return false;
            }
        }
        return true;
    }


    // 请不要超过32皇后问题
    public static int num2(int n) {
        if (n < 1 || n > 32) {
            return 0;
        }
        // 如果你是13皇后问题，limit 最右13个1，其他都是0
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process2(limit, 0, 0, 0);
    }

    // 7皇后问题
    // limit : 0....0 1 1 1 1 1 1 1
    // 之前皇后的列影响：colLim
    // 之前皇后的左下对角线影响：leftDiaLim
    // 之前皇后的右下对角线影响：rightDiaLim
    public static int process2(int limit, int colLim, int leftDiaLim, int rightDiaLim) {
        if (colLim == limit) {
            return 1;
        }
        // pos中所有是1的位置，是你可以去尝试皇后的位置
        int pos = limit & (~(colLim | leftDiaLim | rightDiaLim));
        int mostRightOne = 0;
        int res = 0;
        while (pos != 0) {
            mostRightOne = pos & (~pos + 1);
            pos = pos - mostRightOne;
            res += process2(limit, colLim | mostRightOne, (leftDiaLim | mostRightOne) << 1,
                    (rightDiaLim | mostRightOne) >>> 1);
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 5;

        long start = System.currentTimeMillis();
        System.out.println(num2(n));
        long end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

        start = System.currentTimeMillis();
        System.out.println(num1(n));
        end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

    }
}
