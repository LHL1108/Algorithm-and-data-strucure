package class08;

import java.util.Arrays;

public class Code03_CountSort {
    /*
    要求：
        编写计数排序
    思路：
        1.异常处理，数组为空或长度为1的情况
        2.构建Max加1个桶，把每个数放到桶里，再根据桶取出来
    */
    public static void countSort(int[] arr){
        if (arr == null || arr.length < 2) {
            return;
        }
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < arr.length; i++){
            max = arr[i] > max ? arr[i] : max;
        }

        int[] bucket = new int[max+1];
        for (int i = 0; i < arr.length; i++) {
            bucket[arr[i]]++;
        }
        int idx = 0;
        for (int i = 0; i < bucket.length; i++) {
            while (bucket[i] > 0){
                arr[idx++] = i;
                bucket[i]--;
            }
        }
    }

/////////////////////////////////////////////////////////////////////////////
/*    public static void main(String[] args) {
        int[] test = {3, 6, 4, 8, 1, 5, 9};
        countSort(test);
        System.out.println(Arrays.toString(test));
    }*/

    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }

    // for test
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

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
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

    // for test
    public static void main(String[] args) {
        int testTime = 5000;
        int maxSize = 100;
        int maxValue = 150;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            countSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "测试通过!" : "测试未通过");

    }


}