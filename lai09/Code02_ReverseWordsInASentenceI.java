package lai09;
/*
[Question]
    reverse the string but keep the word's order
[Idea]
    reverse totally first, then reverse each word again
[Notice]
    don't forget the end situation and notice the out of bounding
[Complexity]
    Time:  O(N), we traverse two times
    Space: O(1), only some variables
*/

public class Code02_ReverseWordsInASentenceI {

    public static String reverseWords(String input) {
        char[] arr = input.toCharArray();
        char[] res = reverse(arr, 0, arr.length - 1);
        int i = 0;
        int j = 0;
        while (i < arr.length && j < arr.length) {
            if (j == arr.length - 1 || arr[j + 1] == ' '){
                res = reverse(arr, i, j);
                i = j + 2;
                j = i;
            } else {
                j++;
            }
        }
        return new String(res);
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
        String input = "an apple";
        String output = reverseWords(input);
        System.out.println(output);
    }
}
