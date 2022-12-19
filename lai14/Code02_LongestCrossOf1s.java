package lai14;
/*
[question]
    find the longest arm(same length in 4 direction) of 1 cross in a 1/0 2D array
[idea]
    find left and up arm in each cell
        find left
        find up
        merge
    find right and down arm in each cell
        find right
        find down
        merge
    merge
        compare two input in each position
        update the value with smaller one
        update the global max(only use in main function)
        return global max
    find left
        if it is 1
            i = 0
                1
            0 < i < M - 1
                dp[i-1][j] + 1
        else
            skip
[complexity]
    time: M*N, traverse 2D table 3 times
    space: M*N, dp table
[notice]
    get right down need right and down value, so begin from the right down corner
    getUpLeft inherit the up and left value, getDownRight inherit the down and right value
*/

public class Code02_LongestCrossOf1s {

    public static int largest(int[][] arr){
        if (arr == null) {
            return 0;
        }
        int N = arr.length;
        int M = arr[0].length;
        if (M == 0 || N == 0) {
            return 0;
        }

        int res = 0;
        int[][] upAndLeft = getUpAndLeft(arr, N, M);
        int[][] downAndRight = getDownAndRight(arr, N, M);
        res = merge(upAndLeft, downAndRight, N, M);
        return res;
    }

    public static int[][] getUpAndLeft(int[][] arr, int N, int M) {
        int[][] up = new int[N][M];
        int[][] left = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 1) {
                    if (i == 0 && j == 0) {
                        up[i][j] = 1;
                        left[i][j] = 1;
                    } else if (i == 0) {
                        up[i][j] = 1;
                        left[i][j] = left[i][j - 1] + 1;
                    } else if (j == 0) {
                        up[i][j] = up[i - 1][j] + 1;
                        left[i][j] = 1;
                    } else {
                        up[i][j] = up[i - 1][j] + 1;
                        left[i][j] = left[i][j - 1] + 1;
                    }
                }
            }
        }
        merge(up, left, N, M);
        return up;
    }

    public static int[][] getDownAndRight(int[][] arr, int N, int M) {
        int[][] down = new int[N][M];
        int[][] right = new int[N][M];
        for (int i = N - 1; i >= 0; i--) {
            for (int j = M - 1; j >= 0; j--) {
                if (arr[i][j] == 1) {
                    if (i == N - 1 && j == M - 1) {
                        down[i][j] = 1;
                        right[i][j] = 1;
                    } else if (i == N - 1) {
                        down[i][j] = 1;
                        right[i][j] = right[i][j + 1] + 1;
                    } else if (j == M - 1) {
                        down[i][j] = down[i + 1][j] + 1;
                        right[i][j] = 1;
                    } else {
                        down[i][j] = down[i + 1][j] + 1;
                        right[i][j] = right[i][j + 1] + 1;
                    }
                }
            }
        }
        merge(down, right, N, M);
        return down;
    }

    public static int merge(int[][] arr1, int[][] arr2, int N, int M) {
        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                arr1[i][j] = Math.min(arr1[i][j], arr2[i][j]);
                max = Math.max(max, arr1[i][j]);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int arr[][] = { {0, 0, 0, 0},
                        {1, 1, 1, 1},
                        {0, 1, 1, 1},
                        {1, 0, 1, 1} };
        int[][] arr2 = new int[][]{{0,1,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
        int res = largest(arr2);
        //2
        System.out.println(res);
    }
}
