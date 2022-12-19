package lai13;
/*
[Question]
    Given a word and a dictionary, determine if it can be composed by concatenating words from the given dictionary.
    Dictionary: {“bob”, “cat”, “rob”}, s = “robcatbob”, return true
[Idea]
    dp[i] means each substring starts at 0, and end at i is valid, dp[j] = dp[i-1]valid and i-j is in dict
[Notice]
    what does dp[i] mean: all element BEFORE String idx i is valid, so dp[0] is true, and we should apply for dp[N+1]
    cause we need to consider the first space as true
[Complexity]
    Time: O(n^2)
    Space: O(n)
*/
import java.util.HashSet;
import java.util.Set;

public class Code03_DictionaryWordI {

    public static boolean canBreak(String input, String[] dict) {
        Set<String> set = getSet(dict);
        int n = input.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int j = 1; j < n + 1; j++) {
            for (int i = 0; i < j; i++) {
                if (dp[i] == true && set.contains(input.substring(i, j))) {
                    dp[j] = true;
                    break;
                }
            }
        }
        return dp[n];
    }

    public static Set<String> getSet(String[] dict) {
        Set<String> set = new HashSet<>();
        for (String item : dict) {
            set.add(item);
        }
        return set;
    }


    public static void main(String[] args) {
        String s = "robcatbob";
        String[] dict = {"bob", "cat", "rob"};
        boolean res = canBreak(s, dict);

        // true
        System.out.println(res);

    }
}
