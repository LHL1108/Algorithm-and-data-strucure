package lai09;
/*
[Question]
    replace a part of string, input = "appledogapple", S = "apple", T = "cat", input becomes "catdogcat"
[Idea]
    two pointer, check if can find s by each character as head, if can, replace it, but notice the situation that
    replace a longer substring, that means we need to new a longer string a replace it from tail, notice the index, this is very important
[Notice]
    too many boundaries
*/

import java.util.ArrayList;
import java.util.List;

public class Code04_StringReplaceBasic {
    public static String replace(String input, String s, String t) {
        char[] arr = input.toCharArray();
        if (s.length() >= t.length()) {
            return replaceShorter(arr, s, t);
        } else {
            return replaceLonger(arr, s, t);
        }
    }


    public static String replaceShorter(char[] arr, String s, String t) {
        int fast = 0;
        int slow = 0;
        while (fast < arr.length) {
            if (fast <= arr.length - s.length() && checkSame(arr, fast, s)) {
                replaceByChar(arr, slow, t);
                fast += s.length();
                slow += t.length();
            } else {
                arr[slow++] = arr[fast++];
            }
        }
        return new String(arr, 0, slow); // Bug
    }

    public static String replaceLonger(char[] arr, String s, String t) {

        List<Integer> matches = getMatches(arr, s);
        char[] res = new char[arr.length + matches.size() * (t.length() - s.length())];
        int slow = res.length - 1;
        int fast = arr.length - 1;
        int idx = matches.size() - 1;
        while (fast >= 0) {
            if (idx >= 0 && fast == matches.get(idx)) {
                replaceByChar(res, slow - t.length() + 1, t);
                slow -= t.length();
                fast -= s.length();;
                idx--;
            } else {
                res[slow--] = arr[fast--];
            }
        }
        return new String(res);
    }

    public static boolean checkSame(char[] inputArr, int i, String s) {
        for (int idx = 0; idx < s.length(); idx++) {
            if (inputArr[i + idx] != s.charAt(idx)) {
                return false;
            }
        }
        return true;
    }

    public static void replaceByChar(char[] inputArr, int i, String t) {
        for (int idx = 0; idx < t.length(); idx++) {
            inputArr[i + idx] = t.charAt(idx);
        }
    }

    public static List<Integer> getMatches(char[] arr, String s) {
        List<Integer> matches = new ArrayList<>();
        int i = 0;
        while (i <= arr.length - s.length()) {
            if (checkSame(arr, i, s)) {
                i += s.length();
                matches.add(i - 1);
            } else {
                i++;
            }
        }
        return matches;
    }

    public static void main(String[] args) {
        //abx
        System.out.println(replace("tywjtltyvtyegcboycmqtyq","ty","cmd"));
    }
}
