package lai08;
/*
[Question]
    Determine if a small string is a substring of another large string.
    Return the index of the first occurrence of the small string in the large string.
    Return -1 if the small string is not a substring of the large string.
    “ab” is a substring of “bcabc”, return 2
    “bcd” is not a substring of “bcabc”, return -1
    "" is substring of "abc", return 0
    Both large and small are not null
    If small is empty string, return 0
[Idea]
    use each char in large as head and compare with small
[Notice]
    Don't forget to break when we find the answer
*/

import java.util.HashMap;

public class Code08_DetermineIfOneStringIsAnotherSubstring {
    public static int strstr(String large, String small) {
        if (small.isEmpty()) {
            return 0;
        }
        char[] l = large.toCharArray();
        char[] s = small.toCharArray();
        int res = -1;
        for (int idx = 0; idx < l.length; idx++) {
            res = compare(s, l, idx);
            if (res != -1) {
                break;
            }
        }
        return res;
    }

    public static int compare(char[] s, char[] l, int idx) {
        for(int i = 0; i < s.length; i++) {
            if (idx + i == l.length || s[i] != l[idx + i]) {
                return -1;
            }
        }
        return idx;
    }

    public static void main(String[] args) {
        String large = "bcabc";
        String small = "ab";
        int res = strstr(large, small);
        //2
        System.out.println(res);
    }
}


