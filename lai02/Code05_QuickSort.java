package lai02;
/*
[Question]
    implement a quick sort
[Idea]
    randomly get a number, put it to the right side, put all the elements smaller than it to the left, equal middle, larger right
    and recursively do it to the left part and right part
[Construction]
    main
        cleaning check
        sort process from 0 to n-1
    process
        randomly get an element
        partition by it
        process left part
        process right part
    partition
        two block less and more slit the area into 3 parts
            less
            equal
            larger
[Complexity]
    Time:  O(NlogN) partition logN times, each time O(N), so O(NlogN)
    Space: O(1) only apply for finite variables
*/

import java.util.Arrays;

public class Code05_QuickSort {

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int L, int R) {
        if (L > R || L == R) {
            return;
        }
        swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
        int[] boundaries = partition(arr, L, R);
        process(arr, L, boundaries[0] - 1);
        process(arr, boundaries[1] + 1, R);
    }

    public static int[] partition(int[] arr, int L, int R) {
        int less = L - 1;
        int more = R;
        int idx = L;
        while (idx < more) {
            if (arr[idx] < arr[R]) {
                swap(arr, idx++, ++less);
            } else if (arr[idx] == arr[R]) {
                idx++;
            } else {
                swap(arr, idx, --more);
            }
        }
        return new int[] {less + 1, more - 1};
    }
    public static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {};
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
