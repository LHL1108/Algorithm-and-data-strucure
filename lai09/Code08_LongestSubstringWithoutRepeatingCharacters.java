package lai09;
/*
[Question]
    find the longest length of substring without repeating characters
[Idea]
    two pointers, move and calculate depends on the situation
[Notice]
    we move slow when we find repeating character
    writing down the meaning of variables is always helpful
[Complexity]
    Time:  O(N), 2 pointers traverse the string
    Space: O(N), use a set to mark the character in string
*/


import java.util.HashSet;

public class Code08_LongestSubstringWithoutRepeatingCharacters {

    public static int longest(String input) {
        int i = 0; // beginner character of substring
        int j = 0; // now considering
        int len = 0;
        char[] arr = input.toCharArray();
        HashSet<Character> set = new HashSet<>();
        while (j < arr.length) {
            if (!set.contains(arr[j])) {
                set.add(arr[j]);
                j++;
                len = Math.max(len, j - i);
            } else {
                set.remove(arr[i]);
                i++;
            }
        }
        return len;
    }

    public static void main(String[] args) {
        String input = "efhrgsayekasdanfev";
        int res = longest(input);
        // 9
        System.out.println(res);
    }

}
