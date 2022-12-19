package lai07;
/*
[Question]
    give the number of Parentheses pair, output all the valid Parentheses pair string in a list
    input 2 -> ["()()", "(())"]
[Idea]
    since it is a combination problem, we can try DFS, the layer means the position of the string, each branch means
    we choose to add "(" or ")"
[Complexity]
    Time:  O(2^N), because we have 2N layers,N level, times, each level expand 2 times, so basically only the last level matters, which is 2^N
    Space: O(N), because the depth of recursion tree is N level
*/

import java.util.LinkedList;
import java.util.List;

public class Code02_AllValidPermutationsOfParenthesesI {

    public static List<String> validParentheses(int n) {
        List<String> res = new LinkedList<>();
        if (n == 0) {
            return res;
        }

        StringBuilder sb = new StringBuilder();
        help(0, 0, n, sb, res);
        return res;
    }


    public static void help(int leftNums, int rightNums, int n, StringBuilder sb, List<String> res) {
        if (leftNums == n && rightNums == n) {
            res.add(sb.toString());
            return;
        }

        if (leftNums < n) {
            sb.append("(");
            help(leftNums + 1, rightNums, n, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }

        if (rightNums < leftNums) {
            sb.append(")");
            help(leftNums, rightNums + 1, n, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        int n = 2;
        List<String> res = validParentheses(n);
        for(String item : res) {
            System.out.println(item);
        }

    }
}
