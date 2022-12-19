package class21;
/*
要求：一个二维矩阵，起点为左上角，终点为右下角，只能选择向左或向下移动，求最小路径和
思路：从起点走到当前位置的最小路径和一定是上左面或者上面位置的路径和相对较小的那个再加上自身，而起点位置只有一个值,所以就是它本身，第一行和第一列因为只有一个方向，所以不需要比较两个方向。
     异常判断是两个维度上是否为null或者0长度
代码：
    异常判断
    创建dp
    初始化值
    填好第0行
    填好第0列
    填好剩余
    返回右下角位置

优化：空间压缩，因为此题动态规划中，每个位置最多只依赖左和上，所以，只用一行数组就可以依次迭代推出下面，但要注意方向的选取，选哪个方向最省，根据实际情况需要考虑是否需要用变量记住当前值
代码：
    异常判断
        两个维度是否为null或0长度
    创建一维数组
        搞定第一行
    依次搞定后面每一行直至最后一行
    返回最右值
*/


public class Code01_MinPathSum {
    // minPathSum1
    public static int minPathSum1(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m.length == 0) {
            return 0;
        }

        int[][] dp = new int[m.length][m[0].length];

        dp[0][0] = m[0][0];
        for (int j = 1; j < m[0].length; j++) {
            dp[0][j] = dp[0][j-1] + m[0][j];
        }
        for (int i = 1; i < m.length; i++) {
            dp[i][0] = dp[i-1][0] + m[i][0];
        }
        for (int i = 1; i < m.length; i++) {
            for (int j = 1; j < m[0].length; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + m[i][j];
            }
        }

        return dp[m.length-1][m[0].length-1];
    }

    public static int minPathSum2(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }

        int[] arr = new int[m[0].length];

        arr[0] = m[0][0];
        for (int j = 1; j < m[0].length; j++) {
            arr[j] = arr[j-1] + m[0][j];
        }

        for (int i = 1; i < m.length; i++) {
            arr[0] += m[i][0];
            for (int j = 1; j < m[0].length; j++) {
                arr[j] = Math.min(arr[j-1], arr[j]) + m[i][j];
            }
        }

        return arr[m[0].length - 1];
    }


    // for test
    public static int[][] generateRandomMatrix(int rowSize, int colSize) {
        if (rowSize < 0 || colSize < 0) {
            return null;
        }
        int[][] result = new int[rowSize][colSize];
        for (int i = 0; i != result.length; i++) {
            for (int j = 0; j != result[0].length; j++) {
                result[i][j] = (int) (Math.random() * 100);
            }
        }
        return result;
    }

    // for test
    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int rowSize = 10;
        int colSize = 10;
        int[][] m = generateRandomMatrix(rowSize, colSize);
        System.out.println(minPathSum1(m));
        System.out.println(minPathSum2(m));
    }
}
