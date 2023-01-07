package lai11;
import java.util.LinkedList;
import java.util.List;
/*
[question]
    sprital traverse a 2D matrix and put the elements to the list, like
    1 2 3
    4 5 6 -> [1, 2, 3, 6, 9, 8, 7, 4, 5]
    7 8 9
[idea]
    set up, down, left and right
    while left < right and up < down, traverse 4 sides, and change the doundaries
    deal with the last case
        left == right -> add from left to right
        up == down -> add from up to down
        else -> all done, return
[complexity]
    time: O(M * N)
    space: O(1)
[notice]
    no matter if we return in final if, we need to return at the end of the code outside
    in 2D matrix traverse, we don't move to the end, we move to end - 1, and use < rather than <= end
    in 2 sides, idx--
*/

public class Code06_SpiralOrderTraverseII {

    public static List<Integer> sprital(int[][] matrix) {
        List<Integer> res = new LinkedList<>();
        if (matrix == null || matrix.length == 0) {
            return res;
        }

        int up = 0;
        int down = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;

        while (left < right && up < down) {
            for (int j = left; j < right; j++) {
                res.add(matrix[up][j]);
            }
            for (int i = up; i < down; i++) {
                res.add(matrix[i][right]);
            }
            for (int j = right; j > left; j--) {
                res.add(matrix[down][j]);
            }
            for (int i = down; i > up; i--) {
                res.add(matrix[i][left]);
            }
            // change boundaries...
            up++;
            down--;
            left++;
            right--;
        }

        if (left == right) {
            for (int i = up; i <= down; i++) {
                res.add(matrix[i][left]);
            }
        } else if(up == down) {
            for (int j = left; j <= right; j++) {
                res.add(matrix[up][j]);
            }
        } else {
            return res;
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        List<Integer> res = sprital(matrix);
        //[1, 2, 3, 6, 9, 8, 7, 4, 5]
        System.out.println(res.toString());
    }
}
