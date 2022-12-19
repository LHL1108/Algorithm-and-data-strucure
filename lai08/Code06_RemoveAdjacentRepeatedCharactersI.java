package lai08;
/*
[Question]
    Remove adjacent, repeated characters in a given string, leaving only one character for each group of such characters.
    "aaaabbbc" is transferred to “abc”
    If the given string is null, returning null or an empty string are both valid.
[Idea]
    two pointers, one traverse, one update
[Notice]
    String use .length(), arr use .length
    initial situation judgement
[Complexity]
    Time:  O(n)
    Space: O(1)
*/

public class Code06_RemoveAdjacentRepeatedCharactersI {
    public static String deDup(String input) {
        if (input == null) {
            return null;
        }
        char[] arr = input.toCharArray();
        int i = 0;
        int j = 0;
        while (j < arr.length) {
            if (i == 0 || arr[j] != arr[i - 1]) {
                arr[i++] = arr[j++];
            } else {
                j++;
            }
        }
        return new String(arr, 0, i);
    }

    public static void main(String[] args) {
        String input = "aaaabbbc";
        String res = deDup(input);
        System.out.println(res);
    }

}
