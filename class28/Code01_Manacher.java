package class28;

/*
IDEA
    Aim
        Find the longest palindrome
    Original method
        for each character, expand to both sides and record the longest length
    Core idea
        Use the information calculated in the past to speed up the calculation process of the future information
    process
        the new character is outside the rightmost border
            can't be optimized
        the new character is inside the rightmost border
            can be optimized
                for each new character i, find i' and left most border which symmetry about the center point of rightmost border, than we have 3 situations
                    1) i' 's border is totally inside the leftmost border
                    i palindrome length =  i' palindrome length
                    2) i' s border past the leftmost border
                    i palindrome radius =  i'  to the border
                    3) i' s border is on the leftmost border
                    at least i palindrome radius =  i'  to the border
                    could be longer depends on the situation, we need to check
                Then, we don't need to calculate every character, for some of them, we can get the answer directly !
                After pass through each character, we can get the longest palindrome diameter.
    detail
        O(N^2)  ->  O(N)
        with the help of rightmost border and its mirror left border and i'
    relationship
        Manacher is similar to KMP, both of them use the past information to speed up the calculation of future information.
        But they use different tools, one is rightmost border, one is longest same prefix and suffix length.

FUNCTION
    Main
        abnormal judgement
        pre-processing with add # function
        initialize
            parlindromic radius
            center of symmetry
            rightmost position
            maxium radius points
        processe
            for each element update max radius points
                initial maxradius points
                    min(radius of i', R - i)or 1
                before out of bounds
                    broad the border if you can
                update rightmost and C
                update max radius points
        calculate max radius
            max raidus is max radius poins - 1
        return

    add # function
        turn to char[]
        initial res
        add emlement depends on the position
*/


public class Code01_Manacher {

    public static int manacher(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }
        char[] strs = manacherString(s);
        int max = Integer.MIN_VALUE;
        int R = -1;
        int C = -1;
        // parRadiusPoints
        int[] pArr = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {
            //initial
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
            //process
            while (i + pArr[i] < strs.length && i - pArr[i] > -1) {
                if (strs[i + pArr[i]] == strs[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            //update
            R = i + pArr[i];
            C = i;
            max = Math.max(max, pArr[i]);
        }
        return max - 1 ;
    }

    public static char[] manacherString(String str) {
        char[] strs = str.toCharArray();
        char[] res = new char[2 * strs.length + 1];
        int idx = 0;
        for (int i = 0; i < res.length; i++) {
            if (i % 2 == 0) {
                res[i] = '#';
            } else {
                res[i] = strs[idx++];
            }
        }
        return strs;
    }

    // for test
    public static int right(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = manacherString(s);
        int max = 0;
        for (int i = 0; i < str.length; i++) {
            int L = i - 1;
            int R = i + 1;
            while (L >= 0 && R < str.length && str[L] == str[R]) {
                L--;
                R++;
            }
            max = Math.max(max, R - L - 1);
        }
        return max / 2;
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
        int testTimes = 500000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strSize);
            if (manacher(str) != right(str)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }

}
