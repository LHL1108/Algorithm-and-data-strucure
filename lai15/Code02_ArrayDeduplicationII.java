package lai15;
/*
[question]
    deduplicate a ascending sorted array with a max of 2 elements
[idea]
    two pointers, compare the fast with the element before slow, and copy when not equal
[complexity]
    time: O(n), traverse
    space: O(1), in place
[notice]
    don't forget the input checking
*/

import java.util.Arrays;

public class Code2_ArrayDeduplicationII {

    public static int[] dedup(int[] arr) {
        if (arr.length <= 2) {
            return arr;
        }
        int slow = 1; // final index of result
        for(int fast = 2; fast < arr.length; fast++) {
            if (arr[fast] != arr[slow - 1]) {
                slow++;
                arr[slow] = arr[fast];
            }
        }
        return Arrays.copyOfRange(arr, 0, slow + 1);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 3, 3, 3};
        int[] res = dedup(arr);
        //{1, 2, 2, 3, 3}
        System.out.println(Arrays.toString(res));
    }

}
