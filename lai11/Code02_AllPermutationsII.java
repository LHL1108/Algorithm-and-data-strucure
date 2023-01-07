package lai11;
/*
[question]
    get all permutation of the string, eg. "aab" -> [aab, aba, baa]
[idea]
    use a hashmap to store the cnt of all char
    use DFS to traverse all the possible situation
idx1             a        b
idx2           a   b    a
idx3         b   a    a
         [aab][aba][baa]
[complexity]
    time: O(n * n!) for each word n, has a permutation n!
    space: O(n), hashmap
[notice]
    restore the cnt in dfs
    only process when cnt > 0
    use for each loop to traverse the string
    map.getOrDefault(c, 0) to avoid if else
    don't write 'c' when we use variable c
*/

import java.util.*;

public class Code02_AllPermutationsII {

    public static List<String> permutations(String s) {
        List<String> res = new ArrayList<>();
        if (s == null) {
            return res;
        }
        if (s.length() == 0) {
            res.add("");
            return res;
        }
        Map<Character, Integer> cntMap = getFrequency(s);
        DFS(res, s, cntMap, 0, "");
        return res;
    }


    public static Map<Character, Integer> getFrequency(String s) {
        Map<Character, Integer> cntMap = new HashMap<>();
        for (Character c : s.toCharArray()) {
            cntMap.put(c, cntMap.getOrDefault(c, 0) + 1);
        }
        return cntMap;
    }

    public static void DFS(List<String> res, String s, Map<Character, Integer> cntMap, int idx, String prefix) {
        if (idx == s.length()) {
            res.add(prefix);
            return;
        }

        for (Character c : cntMap.keySet()) {
            if (cntMap.get(c) > 0) {
                int cnt = cntMap.get(c);
                cntMap.put(c, cnt - 1);
                DFS(res, s, cntMap, idx + 1, prefix + c);
                cntMap.put(c, cnt);
            }
        }
    }

    public static void main(String[] args) {
        String input = "aab";
        List<String> res = permutations(input);
        for (String item : res) {
            // [aab aba baa]
            System.out.println(item);
        }
    }

}
