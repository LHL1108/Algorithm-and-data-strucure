package lai14;
/*
[question]
    get the max sum of sub matrix in a 2D matrix
    eg. 1 1 -1      1 1
        1 1 -2  ->  1 1 -> 4
        0 -2 1
[idea]
    first, for each row as up, each valid row as down, calculate the sum for each column, as a 1D array
    second, get the max sum of sub array
[complexity]
    time : O(N * M * M) (N * M for first step, M for second step)
    space: O(M), from 2D to 1D ?
[notice]
    the nature of this question is that we turn the max sum of 2D sub matrix to the max sum of 1D sub accumulated array
    we try to calculate different down with the same up for each for loop of up, the answer must be in it
    don't forget the "new" in creating int[] cur = new int[M]
*/


public class Code06_LargestSubMatrixSum {

    public static int largest(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        if (matrix.length == 1 && matrix[0].length == 1) {
            return matrix[0][0];
        }
        int N = matrix.length;
        int M = matrix[0].length;
        int res = Integer.MIN_VALUE;
        for (int up = 0; up < N; up++) {
            int[] cur = new int[M];
            for (int down = up; down < N; down++) {
                add(cur, matrix[down]);
                res = Math.max(res, max(cur));
            }
        }
        return res;
    }

    public static void add(int[] cur, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            cur[i] += arr[i];
        }
    }

    public static int max(int[] cur) {
        int max = cur[0];
        int tmp = cur[0];
        for (int i = 1; i < cur.length; i++) {
            tmp = Math.max(tmp + cur[i], cur[i]);
            max = Math.max(tmp, max);
        }
        return max;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 1, -1},
                {1, 1, -2},
                {0, -2, 1}
        };
        int res = largest(matrix);
        //4
        System.out.println(res);
    }
}
