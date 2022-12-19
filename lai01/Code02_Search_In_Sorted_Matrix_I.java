package lai01;
import java.util.Arrays;
/*
[QUESTION]
    find the index of target value in a 2D array, if not, return {-1, -1}
[IDEA]
    turn 2D array into 1D, then use binary search
[CONSTRUCTION]
    compute the 1D mid idx
    convert to 2D idx and find the value
    compare
    reduce search area
[NOTICE]
    how to convert 1D idx into 2D
[COMPLEXITY]
    Time: O(logN)  we use binary search
    Space: O(1)    we only apply for several limited value
*/

public class Code02_Search_In_Sorted_Matrix_I {

    public static int[] search(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return new int[] {-1, -1};
        }
        int L = 0;
        int R = matrix[0].length * matrix.length - 1;
        while (L <= R) {
            int mid = L + (R - L) / 2;
            int r = mid / matrix[0].length;
            int c = mid % matrix[0].length;
            if (matrix[r][c] == target) {
                return new int[] {r, c};
            } else if (matrix[r][c] > target) {
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return new int[] {-1, -1};
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 7}, {8, 9, 10}};
        int target = 7;  // [1, 2]
        System.out.println(Arrays.toString(search(matrix, target)));

    }
}
