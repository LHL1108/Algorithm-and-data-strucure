package lai07;
/*
[Question]
    given a string respresenting a char set, output all combination of this set, order doesn't matter, and no need all of chars
[Idea]
    since it is a combination problem, we can try to use DFS to solve it, each layer means each char, each branch means if add it
[Notice]
    String could be null
    need to create a string builder to add rather than manipulate on the input
[Complexity]
    Time:  O(n * 2^n), bottom level of recursion tree has 2^n nodes, and we use O(n) time to create a new string added in result
    Space: O(n), recursion tree level
*/

import java.util.LinkedList;
import java.util.List;

public class Code01_AllSubsetsI {

    public static List<String> subSets(String set) {
        List<String> res = new LinkedList<>();
        if (set == null) {
            return res;
        }

        char[] arr = set.toCharArray();
        StringBuilder sb = new StringBuilder();

        helper(arr, sb, 0, res);
        return res;
    }

    public static void helper(char[] arr, StringBuilder sb, int idx, List<String> res) {
        if (idx == arr.length) {
            res.add(sb.toString());
            return;
        }
        sb.append(arr[idx]);
        helper(arr, sb, idx + 1, res);
        sb.deleteCharAt(sb.length() - 1);

        helper(arr, sb, idx + 1, res);
    }

    public static void main(String[] args) {
        String set = "";
        List<String> res = subSets(set);
        for (String item : res) {
            System.out.println(item);
        }
    }

}
