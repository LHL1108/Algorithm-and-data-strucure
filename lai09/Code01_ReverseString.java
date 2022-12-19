package lai09;
/*
[Question]
    reverse a string and return
[Idea]
    two pointers, one from left, one from right, move adn swap until they meet
[Complexity]
    Time: O(n)
    Space: O(1)
*/

public class Code01_ReverseString {

    public static String reverse(String input) {
        char[] arr = input.toCharArray();
        int i = 0;
        int j = arr.length - 1;
        while (i < j) {
            char tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
            i++;
            j--;
        }
        return new String(arr);
    }

    public static void main(String[] args) {
        String input = "ABCD";
        System.out.println(reverse(input));
    }

}
