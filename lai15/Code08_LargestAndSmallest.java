package lai15;
/*
[question]
    unsorted array, get the max and min value with the min comparation times
[idea]
    first, use n/2 times to swap left side and right side, to get the max to the left part and min to right part
    second, traverse each two part and get max and min
    shrink the 2n to 3/2 * n times
[complexity]
    time: O(n)
    space: O(1)
[notice]
    run a simple case to see if the function works
    the idea of swap and move the max and min to each side
*/

import java.util.Arrays;

public class Code08_LargestAndSmallest {

    public static int[] getMaxAndMin(int[] arr) {
        if (arr == null || arr.length < 1) {
            return arr;
        }
        int n = arr.length;
        for (int i = 0; i <= (n - 1) / 2; i++) {
            if (arr[i] < arr[n - 1 - i]) {
                swap(arr, i, n - 1 - i);
            }
        }
        return new int[] {getMax(arr, 0, (n - 1) / 2), getMin(arr, n / 2, n - 1)};
    }

    public static int getMax(int[] arr, int start, int end) {
        int max = arr[start];
        for (int i = start + 1; i <= end; i++) {
            max = Math.max(arr[i], max);
        }
        return max;
    }

    public static int getMin(int[] arr, int start, int end) {
        int min = arr[start];
        for (int i = start + 1; i <= end; i++) {
            min = Math.min(arr[i], min);
        }
        return min;
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2};
        int[] res = getMaxAndMin(arr);
        // [4, 1]
        System.out.println(Arrays.toString(res));
    }
}
