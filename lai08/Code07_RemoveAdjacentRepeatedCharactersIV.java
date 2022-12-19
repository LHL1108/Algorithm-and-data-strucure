package lai08;
/*
[Question]
    Repeatedly remove all adjacent, repeated characters in a given string from left to right.
    No adjacent characters should be identified in the final string.
    "abbbaaccz" → "aaaccz" → "ccz" → "z"
    "aabccdc" → "bccdc" → "bdc"
[Idea]
    2 pointers, when meet same char, slow move back 1 step, fast move to the next not same char
[Notice]
    the meaning of i, j and their initial position
    the best way to do string is to simulate with paper
[Complexity]
    Time:  O(n)
    Space: O(1)
*/

public class Code07_RemoveAdjacentRepeatedCharactersIV {
    public static String deDup(String input) {
        if (input == null || input.length() < 1) {
            return input;
        }
        int i = -1;
        int j = 0;
        char[] arr = input.toCharArray();
        while (j < arr.length) {
            if (i == -1 || arr[i] != arr[j]) {
                arr[++i] = arr[j++];
            } else {
                i--;
                while (j + 1 < arr.length && arr[j] == arr[j + 1]) {
                    j++;
                }
                j++;
            }
        }
        return new String(arr, 0, i + 1);
    }

    public static void main(String[] args) {
        String input = "abc";
        String res = deDup(input);
        System.out.println(res);
    }
}
