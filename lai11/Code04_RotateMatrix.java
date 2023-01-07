package lai11;
/*
[question]
	rotate the 2D matrix 90° clockwise
[idea]
	from out to inner, change element
	from left to right
[complexity]
	time: O(n^2), traverse
	space: O(1)
[notice]
    i couldn't be right, the best way is to simulate a square
    because i = left, left = r, so i has include the information of r, we don't need to consider r with i
    how to avoid, try r = 1, because when r = 0, no matter if we add it, the result are same
*/


import java.util.Arrays;

public class Code04_RotateMatrix {

    public static void rotate(int[][] matrix) {
        if (matrix == null || matrix.length <= 1) {
            return;
        }

        int n = matrix.length;
        int round = n / 2;
        for (int r = 0; r < round; r++) {
            int left = r;
            int right = matrix.length - 1 - r;
            for (int i = left; i < right; i++) {
                int tmp = matrix[r][i];
                matrix[r][i] = matrix[n - 1 - i][r];
                matrix[n - 1 - i][r] = matrix[n - 1 - r][n - 1 - i];
                matrix[n - 1 - r][n - 1 - i] = matrix[i][n - 1 - r];
                matrix[i][n - 1 - r] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        int[][] input = new int[][]{{1, 2, 3},
                                    {4, 5, 6},
                                    {7, 8, 9}};
        rotate(input);
        /*
        7 4 1
        8 5 2
        9 6 3
        */
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                System.out.print(input[i][j]);
            }
            System.out.println();
        }
    }

}
