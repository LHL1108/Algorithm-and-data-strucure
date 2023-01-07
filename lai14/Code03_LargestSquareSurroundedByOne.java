package lai14;
/*
[question]
    given a 2D 1/0 matrix, return the max length of square that surrounded by 1
    1 1 1
    1 0 1
    1 1 1
    return 3
[idea]
    get up and left matrix
    compare up and left, use the smaller one as the side length
    check if the lowerleft corner has the same up and upperright corner has the same left (side length from length to 1)
    if valid, update the global max
[complexity]
    time: m * n * min(m, n), for each point in 2D matrix, we traverse its side
    space: m * n
[notice]
    use a larger square as left and up, but in for loop, we traverse matrix, so from 0 to M - 1
    when check lowerleft and upperright corner, check if it is >=, not strictly equal
*/


public class Code03_LargestSquareSurroundedByOne {

    public static int largestSquareSurroundedByOne(int[][] matrix) {
        if (matrix == null) {
            return 0;
        }
        int M = matrix.length;
        int N = matrix[0].length;
        if (M == 0 || N == 0) {
            return 0;
        }

        int res = 0;
        int[][] left = new int[M + 1][N + 1];
        int[][] up = new int[M + 1][N + 1];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (matrix[i][j] == 1) {
                    left[i + 1][j + 1] = left[i + 1][j] + 1;
                    up[i + 1][j + 1] = up[i][j + 1] + 1;
                    int side = Math.min(left[i + 1][j + 1], up[i + 1][j + 1]);
                    for (int s = side; s >= 1; s--) {
                        if (up[i + 1][j + 1 - (s - 1)] >= s && left[i + 1 - (s - 1)][j + 1] >= s) {
                            res = Math.max(res, s);
                            break;
                        }
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int arr[][] = { {0, 0, 0, 0},
                        {1, 1, 1, 1},
                        {0, 1, 1, 1},
                        {1, 0, 1, 1} };
        //3
        System.out.println(largestSquareSurroundedByOne(arr));
    }
}
