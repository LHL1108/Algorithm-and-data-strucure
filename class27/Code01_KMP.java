package class27;

/*
Idea: The KMP algorithm is a string algorithm. Its function is to find the first subscript position of the short string in the long string
 (if the substring is not included, it will return -1). In the traditional method, it is necessary to traverse the matching substring from
  the beginning from each subscript position. This method has too high time complexity because it has to re-traverse every time and does not
  reuse any pre-information. The KMP algorithm accelerates the traversal matching process with the information of the maximum matching length
  of the prefix and suffix of each element. The specific method is as follows: first calculate the maximum matching length value of each element's
   prefix and suffix, and then start to match the long and short strings. Once a certain position is matched and it is found that the characters
   are different, it means that the current initial position cannot be used as the final return result of the first position of the substring.
   The traditional algorithm will move one bit to the right at this time to re-match, while the KMP algorithm uses the maximum matching of the
   prefix and suffix. The value length accelerates the jump backwards, jumps directly to the first position of the maximum suffix of the current
   mismatch position, and directly compares the mismatch position, thus omitting two matching processes. One is to omit the matching of the characters
   before the maximum suffix, because is the largest suffix, so there is no situation where the front can be matched. The second is to omit the
   matching from the first character of the largest suffix to the characters before the unmatched position, because the largest suffix and the
   largest prefix are the same, and the substring and the prefix have just been successfully matched.

Frame:
    main
        input judgement
            l null
            s null
            s <= 0
            l < s
        processing
            while l and s are not completely compared
                match
                    continued to match next character
                not match
                    can move (c > 0)
                        move forward s by next[]
                    can not move
                        compared next l character
        output
            judge if s is completely compared
                yes -> location in l - length of s
                no -> -1
    get longest prefix and suffix length
        s.len = 1
            return  result directly
        s.len > 1
            initialization
                next[0] -> -1
                next[1]  -> 0
                filled idx i
                compared idx c
            processing
                equal
                    both ++
                not equal
                    can move
                        move c
                    scan not move
                        0
            output
*/


public class Code01_KMP {

    public static int getIndexOf(String s1, String s2) {
        if (s1 == null || s2 == null || s2.length() < 1 || s1.length() < s2.length()) {
            return -1;
        }
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        int idx1 = 0;
        int idx2 = 0;
        int[] next = getNext(c2);
        while (idx1 < c1.length && idx2 < c2.length) {
            if (c1[idx1] == c2[idx2]) {
                idx1++;
                idx2++;
            } else if (idx2 > 0) {
                idx2 = next[idx2];
            } else {
                idx1++;
            }
        }
        return idx2 == s2.length() ? idx1 - s2.length() : -1;
    }
    public static int[] getNext(char[] c2) {
        if (c2.length == 1) {
            return new int[] {-1};
        }
        int[] next = new int[c2.length];
        next[0] = -1;
        next[1] = 0;
        int c = 0;
        int i = 2;
        while (i < c2.length) {
            if (c2[i-1] == c2[c]) {
                next[i++] = ++c;
            } else if(c > 0) {
                c = next[c];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

    // for test
    public static String getRandomString(int possibilities, int size) {
        char[] ans = new char[(int) (Math.random() * size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) {
        int possibilities = 5;
        int strSize = 20;
        int matchSize = 5;
        int testTimes = 5000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strSize);
            String match = getRandomString(possibilities, matchSize);
            if (getIndexOf(str, match) != str.indexOf(match)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }

}
