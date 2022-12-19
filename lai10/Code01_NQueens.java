package lai10;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
/*
[Question]
    N Queens problem, return a list in list, each idx is row, value is col
[Idea]
    solve with dfs, determine which col is valid to place in each row, keep some valid solutions by situation
[Notice]
    use .get() in List
    new a List when append cur to res
    row == n is the stopping situation
[Complexity]
    Time:  O(n!*n), available branches is getting less, and finally there will be a copy result
    Space: O(n), the depth of recursion tree
*/


public class Code01_NQueens {

    public static List<List<Integer>> nqueens(int n) {
        List<Integer> cur = new LinkedList<>();
        List<List<Integer>> res = new LinkedList<>();

        process(n, 0, cur, res);
        return res;
    }

    public static void process(int n, int row, List<Integer> cur, List<List<Integer>> res) {
        if (row == n) {
            res.add(new ArrayList<>(cur));
        }
        for(int col = 0; col < n; col++) {
            if (valid(row, col, cur)) {
                cur.add(col);
                process(n, row + 1, cur, res);
                cur.remove(cur.size() - 1);
            }
        }
    }

    public static boolean valid(int row, int col, List<Integer> cur) {
        for (int i = 0; i < cur.size(); i++) {
            if (row == i || col == cur.get(i) || Math.abs(row - i) == Math.abs(col - cur.get(i))) {
                return false;
            }
        }
        return true;
    }




    public static void main(String[] args) {
        List<List<Integer>> res = nqueens(8);
        //92
        System.out.println(res.size());
        int[] arr = new int[5];
        List<int[]> list = Arrays.asList(arr);
    }
}
