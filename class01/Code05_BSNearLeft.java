package class01;

import java.util.Arrays;

public class Code05_BSNearLeft {
    // 寻找有序数组中 >= val 最左的位置
/*    public static int nearestIndex(int[] arr, int val) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        int L = 0;
        int R = arr.length - 1;
        int idx = -1;
        while (L <= R) {
            int mid = L + (R - L) / 2;
            if (arr[mid] >= val) {
                idx = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return idx;
    }*/
/*

    public static int nearestIndex(int[] arr, int val) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        int L = 0;
        int R = arr.length - 1;
        if (arr[R] < val) {
            return -1;
        }
        while (L < R) {
            if (arr[L] >= val) {
                R = L;
                break;
            }
            int mid = L + (R - L) / 2;
            if (arr[mid] >= val) {
                R = mid;
            } else {
                L = mid + 1;
            }
        }
        return L;

    }
*/
    public static int nearestIndex(int[] arr, int val) {
        if (arr == null || arr.length < 1 || arr[arr.length - 1] < val) {
            return -1;
        }
        int L = 0;
        int R = arr.length - 1;
        while (L < R) {
            int mid = L + (R - L) / 2;
            if (arr[mid] >= val) {
                R = mid;
            } else {
                L = mid + 1;
            }
        }
        return L;
    }

    // for test
    public static int test(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= value) {
                return i;
            }
        }
        return -1;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            Arrays.sort(arr);
            int value = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
            if (test(arr, value) != nearestIndex(arr, value)) {
                printArray(arr);
                System.out.println(value);
                System.out.println(test(arr, value));
                System.out.println(nearestIndex(arr, value));
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
