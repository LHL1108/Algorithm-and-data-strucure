package lai02;
/*
[Question]
    compliment merge sort
[Idea]
    main
        recursion + merge
    merge
        move the smaller element
        move the rest
        copy
[Construction]
    main
        cleaning check
        process from 0 to n-1
    process
        process from 0 to mid
        process from mid + 1 to n-1
        merge
    merge
        initialize
        move the smaller one before out of range
        move the rest of left part before out of range
        move the rest of right part before out of range
        copy help to arr
[Notice]
    if there is L and R in input, then idx starts from L, not 0
    add idx after move smaller
    notice boundary rules
    how to copy array
[Complexity]
    Time: O(NlogN), because each move smaller is O(N), it appears logN times
    Space: O(N) , because we use help array to get whole sorted array
*/

import java.util.Arrays;

public class Code04_MergeSort {


    public static int[] mergeSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return arr;
        }
        process(arr, 0, arr.length - 1);
        return arr;
    }

    public static void process(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int M = L + (R - L) / 2;
        process(arr, L, M);
        process(arr, M + 1, R);
        merge(arr, L, M, R);
    }

    public static void merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int i = L;
        int j = M + 1;
        int idx = 0;
        while (i <= M && j <= R) {
            help[idx++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
        }
        while (i <= M) {
            help[idx++] = arr[i++];
        }
        while (j <= R) {
            help[idx++] = arr[j++];
        }
        for (int t = 0; t < R - L + 1; t++) {
            arr[L + t] = help[t];
        }
    }


    public static void main(String[] args) {
        int[] arr = new int[] {2, 1, 3, 4};
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
