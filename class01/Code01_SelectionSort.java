package class01;

import java.util.Arrays;

public class Code01_SelectionSort {
/*
  Interview Question1 :
        Please explain the principle of selection sort and implement it with code.

    Interview Question2 :
        write a logarithm
                write a random array with a size of maxSize and a maximum value is maxValue
                write a method to copy a array
                write a method to determine whether two arrays are equal
                write a method to print each element in an array
                write a building array sorting method
        test the logarithm for 100 times and output the successfull example

  Answer:
      Finding the smallest value in the remaining array and keep putting it at the end of the sorted array
*/

    public static void selectionSort(int[] arr) {
//      determine whether the length of the array meets the requirements.
        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i = 0; i < arr.length; i++) {
//          the smallest value
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr, i, minIndex);
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
/*
        int[] test1 = {3, 2, 1, 4};
        selectionSort(test1);
        for (int i = 0; i < test1.length; i++) {
            System.out.println(test1[i]);
        }

        int[] test2 = generateRandomArray(2, 2);
        for (int i = 0; i < test2.length; i++) {
            System.out.println(test2[i]);
        }


        int[] test3 = {3, 2, 1, 7};
        int[] test4 = copyArray(test3);
        for (int i = 0; i < test4.length; i++) {
            System.out.println(test4[i]);
        }

        int[] test5 = {4, 5, 6, 7};
        int[] test6 = {4, 5, 6};
        System.out.println(isEqual(test5, test6));


        int[] test7 = {4, 5, 6, 7};
        printArray(test7);

*/

        int testTime = 1000;
        int maxSize = 5;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {

            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            selectionSort(arr1);
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
        selectionSort(arr);
        printArray(arr);
    }


}
