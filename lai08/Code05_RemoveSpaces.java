package lai08;
/*
[Question]
    remove all leading/trailing/duplicated empty spaces in a String
    Eg.  “  a” --> “a”       ,    “   I     love MTV ” --> “I love MTV”
    The given string is not null.
[Idea]
    2 pointers, traverse the char array, delete spaces in two part, 2 situation head and middle
[Notice]
    character space is ' ', not " "
    know the meaning of slow, meet ' ' compare with slow - 1
    best way of evaluation is to simulate it and consider spacial situation such as "    "
[Complexity]
    Time: O(N), traverse the string
    Space: O(1), operate on the string itself
*/

public class Code05_RemoveSpaces {

    public static String removeSpaces(String input) {
        int fast = 0;
        int slow = 0;

        char[] arr = input.toCharArray();
        while (fast < arr.length && arr[fast] == ' ') {
            fast++;
        }
        if (fast >= arr.length) {
            return "";
        }
        arr[slow++] = arr[fast++];


        while (fast < arr.length) {
            if (arr[fast] == ' ' && arr[slow - 1] == ' ') {
                fast++;
            } else {
                arr[slow++] = arr[fast++];
            }
        }

        String res = arr[slow - 1] == ' ' ? new String(arr, 0, slow - 1) : new String(arr, 0, slow);
        return res;
    }

    public static void main(String[] args) {
        String input = "   I    Love u  ";
        String res = removeSpaces(input);
        System.out.println(res);
    }
}
