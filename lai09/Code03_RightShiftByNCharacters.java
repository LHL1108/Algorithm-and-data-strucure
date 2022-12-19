package lai09;
/*
[Question]
    shift n characters in string
[Idea]
    first, calculate the new smaller n, then reverse left and right part
[Complexity]
    Time: O(n), reverse is n
    Space:O(1), no extra data structure
[Notice]
    string's length is .length()
    don't forget to traverse all at the beginning
*/

public class Code03_RightShiftByNCharacters {

    public static String rightShift(String input, int n) {
        if (input == null || input.length() == 0 || n == 0) {
            return input;
        }
        char[] arr = input.toCharArray();
        int shift = n % arr.length;
        arr = reverse(arr, 0, arr.length - 1);
        arr = reverse(arr, 0, shift - 1);
        arr = reverse(arr, shift, arr.length - 1);
        return new String(arr);
    }

    public static char[] reverse(char[] arr, int i, int j) {
        while (i < j) {
            char tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
            i++;
            j--;
        }
        return arr;
    }

    public static void main(String[] args) {
        String input = "abcdef";
        String res = rightShift(input, 2);
        //"efabcd"
        System.out.println(res);
    }
}
