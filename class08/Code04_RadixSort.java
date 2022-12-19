package class08;

import java.util.Arrays;

public class Code04_RadixSort {
    /*
    要求：
        编写基数排序
    思路：
        1.先对所有数据根据个位排，再对所有数据根据数据十位排，再对所有数据根据百位排
        2.设计一个函数getRadix，可以求出数的最大位数，
        3.如何将一串数根据某一位排好：
            先确定有所少数以该数字结尾，再确定有多少小于等于该数字的数以该数字结尾
            从右往左遍历，如果出现一个数后，将其放在小于等于该数字的数以该数字结尾的最右，数字-1
    易错：
        循环边界条件
        是每一轮调整都复刻一边数组
    */
    public static void radixSort(int[] arr) {
        if (arr==null || arr.length < 2) {
            return;
        }
        int maxbits = getMaxBits(arr);
        radixSortMethod(arr, 0, arr.length-1, maxbits);
    }

    public static int getMaxBits(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int ans = 0;
        while (max != 0) {
            ans++;
            max /= 10;
        }
        return ans;
    }

    public static int getDigit(int num, int d) {
        return (int)(num / Math.pow(10, d - 1)) % 10;
    }

    public static void radixSortMethod(int[] arr, int L, int R, int maxbits) {
        for (int d = 1; d <= maxbits; d++) {
            int i = 0, j = 0;
            int digit = 10;
            int[] help = new int[R - L + 1];
            int[] cnt = new int[digit];
            for (i = 0; i < arr.length; i++) {
                j = getDigit(arr[i], d);
                cnt[j]++;
            }
            for (j = 1; j <= cnt.length - 1 ; j++) {
                cnt[j] = cnt[j - 1] + cnt[j];
            }
            for (i = R; i >= L; i--) {
                j = getDigit(arr[i], d);
                help[cnt[j] - 1] = arr[i];
                cnt[j]--;
            }
            for (i = L, j = 0; i <= R; i++, j++) {
                arr[i] = help[j];
            }
        }
    }



    //////////////////////////////////////////////////////////////////
    // for test

/*
    public static void main(String[] args) {
        int[] test = {15, 293, 782, 64, 78, 3, 14};
        radixSort(test);
        System.out.println(Arrays.toString(test));
    }
  */
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
        int maxValue = 100000;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            radixSort(arr1);
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
