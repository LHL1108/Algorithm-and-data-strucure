package lai02;
/*
[Question]
    move all the 0s to the right side, don't need to maintain the order of other numbers, [0, 1, 0, 2] -> [1, 2, 0, 0] or [2, 1, 0, 0]
[Idea]
    same method of partition, create a pointer moving from left to right, and a current 0 position pointer from right to left
    swap the elements when finding a 0 until they meet.
[Construction]
    cleaning check
        null
        empty
    initialize two pointers
    before they meet
        find zero
            swap
            move pointer
        not zero
            move pointer
[Complexity]
    Time: O(N) , because we traversed array
    Space: O(1), because we only apply for infinity variables
[Notice]
    when we find a 0, we don't need to move i, because we haven't seen the new number before.
    partition process's swap will mess up the other numbers' order
*/

import java.util.Arrays;

public class Code06_Move_0s_To_The_End_I {

    public static int[] moveZero(int[] arr) {
        if (arr == null || arr.length == 0) {
            return arr;
        }

        int i = 0;
        int j = arr.length;
        while (i < j) {
            if (arr[i] == 0) {
                swap(arr, i, --j);
            } else {
                i++;
            }
        }
        return arr;
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 0, 2, 3, 3, 0};
        int[] res = moveZero(arr);
        System.out.println(Arrays.toString(res));
    }
}
