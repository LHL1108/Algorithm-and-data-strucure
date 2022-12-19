package lai02;
/*
[Question]
    move all -1 to the left, 0 in the middle, 1 to the right. eg, {1, 0, 1, -1, 0} is sorted to {-1, 0, 0, 1, 1}
[Idea]
    use partition idea, pivot is 0
[Construction]
    cleaning check
        null
        empty
    initialization
        l,r,idx
    traverse before l r meet
        smaller than 0
        equal to 0
        larger than 0
[Complexity]
    Time: O(N) , because we traverse the array
    Space: O(1), because we use several variables
[Notice]
    the movement of i and j
*/

import java.util.Arrays;

public class Code07_Rainbow_Sort {

    public static int[] rainbowSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return arr;
        }

        int l = -1;
        int r = arr.length;
        int idx = 0;

        while (idx < r) {
            if (arr[idx] == -1) {
                swap(arr, ++l, idx++);
            } else if (arr[idx] == 0) {
                idx++;
            } else {
                swap(arr, idx, --r);
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
        int[] arr = {1, 0, 1, -1, 0};
        int[] res = rainbowSort(arr);
        System.out.println(Arrays.toString(res));
    }
}
