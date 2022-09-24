package class01;

import java.util.Arrays;

public class Code02_BubbleSort {
    public static void bubbleSort(int[] arr) {
        for (int e = arr.length - 1; e > 0 ; e--) {
            for (int i = 0; i < e; i++) {
                if (arr[i] > arr[i+1]) {
                    swap(arr, i, i+1);
                }
            }

        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    public static int[] generateRandomArray(int maxSize, int maxValue) {

//      math.random [0, 1)
//        math.random * maxSize [0, maxSize)
//        (int) math.random * maxSize [0, maxSize - 1]
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) ((maxValue) * Math.random());
        }
        return arr;
    }

    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    public static boolean isEqual(int[] arr1, int[] arr2) {
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if ((arr1 != null && arr2 == null) || (arr1 == null && arr2 != null)) {
            return false;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void comparator(int[] arr) {Arrays.sort(arr);}


    public static void main(String[] args) {
        int testTime = 100000;
        int maxSize = 50;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {

            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            bubbleSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice" : "Fucking fucked");


        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        bubbleSort(arr);
        printArray(arr);
    }
}
