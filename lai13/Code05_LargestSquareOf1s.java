package lai13;
/*
[Question]
    Determine the largest square of 1s in a binary matrix (a binary matrix only contains 0 and 1), return the length of the largest square.
     {{0, 0, 0, 0},
      {1, 1, 1, 1},
      {0, 1, 1, 1},
      {1, 0, 1, 1}}    return 2
[Idea]
    the square must has a right down corner, we just consider the situation that every element be a corner
    the answer must be in them. And it will give us the relationship, between the f[i, j] and its nearest neighbour
    f[i, j] is min of (f[i-1, j], f[i, j-1], f[i-1, j-1]) + 1
[Notice]
    Don't forget the simple situation, i,j is 0
    make sure what should be return, corner or global max
[Complexity]
    Time:  n^2
    Space: n^2
*/


public class Code05_LargestSquareOf1s {

    public static int largest(int[][] arr){
        int rows = arr.length;
        int cols = arr[0].length;
        int[][] dp = new int[rows][cols];
        int max = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (arr[i][j] == 0) {
                    dp[i][j] = 0;
                } else {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
                    }
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[][] arr = {{1,1,1,1},
                       {1,1,1,0},
                       {1,1,1,1},
                       {1,1,0,1}};
        int res = largest(arr);
        System.out.println(res);
    }

}
