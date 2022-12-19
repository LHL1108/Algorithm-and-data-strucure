package lai02;

/*
[Question]
    implement selection sort
[Idea]
    for each iteration, find the smallest element in unsorted array and put it on its left side
[Construction]
    smallest position loop
        find loop
            initialize
            update smallest position
        swap smallest position and left of unsorted
[Notice]
    compare from i + 1 to tail with i equals to get min value from i to tail
[Complexity]
    Time: O(N^2), two for loops
    Space: O(1), only apply for finite variables
*/

import java.util.Arrays;

public class Code03_SelectionSort {

    public static int[] selectionSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return arr;
        }
        for (int i = 0; i <= arr.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j <= arr.length - 1; j++) {
                minIdx = arr[j] < arr[minIdx] ? j : minIdx;
            }
            swap(arr, minIdx, i);
        }
        return arr;
    }

    public static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }


    public static void main(String[] args) {
        int[] arr = new int[] {1, 3, 2, 5, 4} ;
        selectionSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
