package lai15;
/*
[question]
    deduplicate the sorted array
[idea]
    2 pointers, slow is the last deduplicated array index, fast is current index we are seeing
    if they are not equal, copy the value of fast to slow, until we have seen all the elements
    finally, we return the subarray from 0 to n (use Arrays.copyOfRange(arr, 0, n + 1))
[complexity]
    time: O(n), we traverse with two pointers
    space: O(1), we do it inplace
[notice]
    the meaning of slow and fast
    Arrays.copyOfRange(arr, 0, n + 1), idx n+1 is not included
*/

import java.util.Arrays;

public class Code1_ArrayDeduplicationI {

    public static int[] dedup(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }
        int slow = 0; // the final idx of deduplicated array
        for (int fast = 1; fast < arr.length; fast++) {
            if (arr[fast] != arr[slow]) {
                slow++;
                arr[slow] = arr[fast];
            }
        }
        return Arrays.copyOfRange(arr, 0, slow + 1);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 3};
        int[] res = dedup(arr);
        // 123
        System.out.println(Arrays.toString(res));
    }
}
