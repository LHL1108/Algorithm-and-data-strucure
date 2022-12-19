package lai13;
/*
[Question]
    find the min edit distance from s1 to s2, including replace, add and remove, each of them is 1 step
[Idea]
    consider the rest string need to be match after each change, if i and j are the length of strings,replace i-1, j-1,
    add i, j - 1 (because we add and didn't change the source s1, so still i), remove i-1, j(because we only change s1)
    so i, j is the min of them plus 1, use this relation shape to fill the form
[Notice]
    i, j means length, so it could be used in charAt
    f[i][j] = f[i-1][j-1] if tails are the same, not f[0][0]
[Complexity]
    Time: O(m*n), two for loop traverse m and n
    Space: O(m*n), 2D array of m * n
*/

public class Code04_EditDistance {

    public static int editDistance(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] f = new int[m + 1][n + 1];
        for (int i = 0; i < m + 1; i++) {
            f[i][0] = i;
        }
        for (int j = 0; j < n + 1; j++) {
            f[0][j] = j;
        }
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    f[i][j] = f[i-1][j-1];
                } else {
                    int min = Math.min(f[i-1][j], Math.min(f[i][j-1], f[i-1][j-1]));
                    f[i][j] = min + 1;
                }
            }
        }
        return f[m][n];
    }


    public static void main(String[] args) {
        String s1 = "ab";
        String s2 = "dbbabc";

        System.out.println(editDistance(s1, s2));
    }
}
