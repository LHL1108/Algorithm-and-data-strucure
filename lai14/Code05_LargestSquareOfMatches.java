package lai14;
/*
[question]
    we need to find the largest square in a match table,0-no match, 1-one right match, 2-one down match, 3-both
[idea]
    figure out each point's largest right matches, and largest down matches
    choose the smaller one as the square side
    check other 2 points if the square is valid
    update global max
    return max
[complexity]
    time: m * n, traver the 2D matrix several times
    space: m * n, we build an extra 2D matrix
[notice]
    why use a larger map as right and down, because we need to inherit / plus one at the beginning
    figure out, side is the largest one, after that we compare with s, not side
    don't forget to update max
    we fill from lower right corner, and check upper right and lower left of the square
*/

public class Code05_LargestSquareOfMatches {

    public static int largestSquareOfMatches(int[][] matrix) {
        if (matrix == null) {
            return 0;
        }
        int M = matrix.length;
        if (M == 0) {
            return 0;
        }
        int N = matrix[0].length;
        if (N == 0) {
            return 0;
        }

        int[][] right = new int[M + 1][N + 1];
        int[][] down = new int[M + 1][N + 1];
        int max = 0;
        for (int i = M - 1; i >= 0; i--) {
            for (int j = N - 1; j >= 0; j--) {
                if (hasRight(matrix, i, j)) {
                    right[i][j] = right[i][j + 1] + 1;
                }
                if (hasDown(matrix, i, j)) {
                    down[i][j] = down[i + 1][j] + 1;
                }
                if (hasBoth(matrix, i, j)) {
                    int side = Math.min(right[i][j], down[i][j]);
                    for (int s = side; s >= 1; s--) {
                        if (right[i + s][j] >= s && down[i][j + s] >= s) {
                            max = Math.max(max, s);
                            break;
                        }
                    }
                }
            }
        }
        return max;
    }

    public static boolean hasRight(int[][] matrix, int i, int j) {
        if (matrix[i][j] == 1 || matrix[i][j] == 3) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean hasDown(int[][] matrix, int i, int j) {
        if (matrix[i][j] == 2 || matrix[i][j] == 3) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean hasBoth(int[][] matrix, int i, int j) {
        if (matrix[i][j] == 3) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{3, 1, 2},
                          {2, 0, 2},
                          {1, 1, 0}};
        int res = largestSquareOfMatches(matrix);
        //2
        System.out.println(res);

    }
}
