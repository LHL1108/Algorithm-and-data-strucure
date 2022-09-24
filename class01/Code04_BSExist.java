package class01;

import java.util.Arrays;

public class Code04_BSExist {
    public static boolean exist(int[] sortedArr, int num) {
        if (sortedArr == null || sortedArr.length < 1) {
            return false;
        }
        int L = 0;
        int R = sortedArr.length - 1;

        while (L < R) {
            int mid = L + (R - L) / 2;
            if (sortedArr[mid] >= num) {
                R = mid;
            }
            else {
                L = mid + 1;
            }
        }

        return sortedArr[L] == num;
    }

    // for test
    public static boolean test(int[] sortedArr, int num) {
        for(int cur : sortedArr) {
            if(cur == num) {
                return true;
            }
        }
        return false;
    }


    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
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
            if (test(arr, value) != exist(arr, value)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }


/*    public static void main(String[] args) {
        int [] test1 = {1, 3, 5, 7, 9};
        int [] test2 = null;
        int [] test3 = {};
        boolean ans = exist(test2, -1);
        System.out.println(ans);
    }*/
}


/*
{1, 3, 5, 7, 9} find 2 return false
第一步: 计算中间值5
第二部：5 > 2
第三步: 改变左右边界直至相等
第四步：比较最终值，判断是否相等*/
