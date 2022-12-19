package lai09;
/*
[Question]
    give a string and a abbreviation, judge if they match
    eg. pattern “s11d” matches input “sophisticated” since “11” matches eleven chars “ophisticate”.
[Idea]
    meet character
        move together
    meet number
        get all number
        move string
[Complexity]
    Time: O(n), traverse
    Space: O(1)
[Notice]
    we should judge OOB in while loop with i++ or j++
*/

public class Code05_StringAbbreviationMatching {

    public static boolean match(String input, String pattern) {
        int i = 0;  // input char considering
        int j = 0;  // pattern char considering
        char[] arr1 = input.toCharArray();
        char[] arr2 = pattern.toCharArray();
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] == arr2[j]) {
                i++;
                j++;
            } else if (arr2[j] >= '0' && arr2[j] <= '9'){
                int skip = 0;
                while (j < arr2.length && arr2[j] >= '0' && arr2[j] <= '9') {
                    skip = skip * 10 + (arr2[j] - '0');
                    j++;
                }
                while (skip > 0){
                    i++;
                    skip--;
                }
            } else {
                return false;
            }
        }
        if (i == arr1.length && j == arr2.length) {
            return true;
        } else {
            return false;
        }
    }


    public static void main(String[] args) {
        String input = "abcdef";
        String pattern = "a2d2";
        // true
        System.out.println(match(input, pattern));
    }

}
