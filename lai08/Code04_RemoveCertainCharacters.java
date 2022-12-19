package lai08;
/*
[Question]
    Remove given characters in input string, the relative order of other characters should be remained. Return the new string after deletion.
    input = "abcd", t = "ab", delete all instances of 'a' and 'b', the result is "cd".
    The given input string is not null.
    The characters to be removed is given by another string, it is guaranteed to be not null.
[Idea]
    use a set to collect char in t and 2 pointer reconstruct input by set
[Notice]
    char is Character in Java
    if use 2 pointer to change source data structure, notice the return range
[Complexity]
    Time:  O(m+n), we traverse two strings
    Space: O(n), we create a deleted set
*/

import java.util.HashSet;
import java.util.Set;

public class Code04_RemoveCertainCharacters {



    public static String remove(String input, String t) {
        Set<Character> set = collectDeletedChar(t);
        char[] arr = input.toCharArray();
        int slow = 0;
        int fast = 0;
        while (fast < arr.length) {
            if (!set.contains(arr[fast])) {
                arr[slow++] = arr[fast++];
            } else {
                fast++;
            }
        }
        return new String(arr, 0, slow);
    }

    public static Set<Character> collectDeletedChar(String t) {
        Set<Character> set = new HashSet<>();
        char[] arr = t.toCharArray();
        for (int i = 0; i < arr.length; i ++) {
            if (!set.contains(arr[i])) {
                set.add(arr[i]);
            }
        }
        return set;
    }

    public static void main(String[] args) {
        String input = "abcccdea";
        String t = "ace";
        String res = remove(input, t);

        // "bd"
        System.out.println(res);
    }
}
