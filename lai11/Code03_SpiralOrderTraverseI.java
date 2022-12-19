package lai11;
import java.util.ArrayList;
import java.util.List;

/*
[Question]
    traverse a 2D array in spiral order,
         eg.  {{1,  2,  3},
               {4,  5,  6},
               {7,  8,  9}}
    the traversal sequence is [1, 2, 3, 6, 9, 8, 7, 4, 5]
[Idea]
    since the we could reduce the problem size from total array to each layer, so we can use recursion
    the base case is size is 1 or 0
[Notice]
    remember to return in base case
    be careful of the index of corner
[Complexity]
    Time: O(n^2), the total elements of array
    Space: O(n), since it is a recursion, the depth call stack is the diagonal
*/


public class Code03_SpiralOrderTraverseI {

    public static List<Integer> spiral(int[][] arr) {

        int start = 0;
        int size = arr.length;

        List<Integer> res = new ArrayList<>();
        walkAround(arr, start, size, res);
        return res;
    }

    public static void walkAround(int[][] arr, int start, int size, List<Integer> res) {
        if (size == 0) {
            return;
        }
        if (size == 1) {
            res.add(arr[start][start]);
            return;
        }

        for (int step = 0; step < size - 1; step++) {
            res.add(arr[start][start + step]);
        }

        for (int step = 0; step < size - 1; step++) {
            res.add(arr[start + step][start + size - 1]);
        }

        for (int step = 0; step < size - 1; step++) {
            res.add(arr[start + size - 1][start + size - 1 - step]);
        }

        for (int step = 0; step < size - 1; step++) {
            res.add(arr[start + size - 1 - step][start]);
        }
        walkAround(arr, start + 1, size - 2, res);
    }

    public static List<Integer> spiral2(int[][] arr) {

        int N = arr.length;
        int up = 0;
        int right = N - 1;
        int down = N - 1;
        int left = 0;

        int i = 0;
        int j = 0;

        int idx = 0;
        String[] direction = {"go right", "go down", "go left", "go up"};
        List<Integer> res = new ArrayList<>();

        while (i >= up && i <= down && j >= left && j <= right) {
            if (direction[idx] == "go right") {
                res.add(arr[i][j]);
                if (j == right) {
                    up++;
                    idx++;
                    i++;
                } else {
                    j++;
                }
            } else if (direction[idx] == "go down") {
                res.add(arr[i][j]);
                if (i == down) {
                    right--;
                    idx++;
                    j--;
                } else {
                    i++;
                }
            } else if (direction[idx] == "go left") {
                res.add(arr[i][j]);
                if (j == left) {
                    down--;
                    idx++;
                    i--;
                } else {
                    j--;
                }
            } else {
                res.add(arr[i][j]);
                if (i == up) {
                    left++;
                    idx = 0;
                    j++;
                } else {
                    i--;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] matrix = { {1,  2,  3},
                           {4,  5,  6},
                           {7,  8,  9} };
        List<Integer> res = spiral(matrix);
        // 123698745
        for (int item : res) {
            System.out.println(item);
        }
    }
}
