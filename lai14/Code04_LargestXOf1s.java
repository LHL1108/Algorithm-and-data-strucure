package lai14;
/*
[question]
    get the longest arm of X of 1 in a 1/0 2D matrix
[idea]
    get the arm length of the upper part by merge left and right
    get the arm length of the lower part by merge left and right
    merge them
[complexity]
    time: m * n, traverse the matrix 3 times
    space: m * n, extra upper and lower part
[notice]
    when move from lower right corner, i and j --, not ++
    don't forget +1 when inherit from other

*/

public class Code04_LargestXOf1s {

    public static int largestX(int[][] matrix) {
        if (matrix == null) {
            return 0;
        }
        int N = matrix.length;
        if (N == 0) {
            return 0;
        }
        int M = matrix[0].length;
        if (M == 0) {
            return 0;
        }
        int[][]upper = getUpper(matrix, N, M);
        int[][]lower = getLower(matrix, N, M);
        return merge(upper, lower, N, M);
    }

    public static int[][] getUpper(int[][] matrix, int N, int M){
        int[][] upperleft = new int[N][M];
        int[][] upperright = new int[N][M];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if (matrix[i][j] == 1) {
                    upperleft[i][j] = getNumber(upperleft, i - 1, j - 1, N, M) + 1;
                    upperright[i][j] = getNumber(upperright, i - 1, j + 1, N, M) + 1;
                }
            }
        }
        merge(upperleft, upperright, N, M);
        return upperleft;
    }

    public static int[][] getLower(int[][] matrix, int N, int M){
        int[][] lowerleft = new int[N][M];
        int[][] lowerright = new int[N][M];
        for(int i = N - 1; i >= 0; i--) {
            for (int j = M - 1; j >= 0; j--) {
                if (matrix[i][j] == 1) {
                    lowerleft[i][j] = getNumber(lowerleft, i + 1, j - 1, N, M) + 1;
                    lowerright[i][j] = getNumber(lowerright, i + 1, j + 1, N, M) + 1;
                }
            }
        }
        merge(lowerleft, lowerright, N, M);
        return lowerleft;
    }

    public static int merge(int[][] upper, int[][] lower, int N , int M) {
        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                upper[i][j] = Math.min(upper[i][j], lower[i][j]);
                max = Math.max(max, upper[i][j]);
            }
        }
        return max;
    }

    public static int getNumber(int[][] matrix, int x, int y, int N, int M) {
        if (x < 0 || x > N - 1 || y < 0 || y > M - 1) {
            return 0;
        }
        return matrix[x][y];
    }

    public static void main(String[] args) {
        int[][] matrix = { {1, 0, 1},
                           {0, 1, 0},
                           {1, 0, 1}};
        int res = largestX(matrix);
        //2
        System.out.println(res);
    }
}
