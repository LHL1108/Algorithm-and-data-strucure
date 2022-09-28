package class05;

import java.util.Arrays;

public class Code02_PartitionAndQuickSort {
    /*
    要求： 编写最优版本的快速排序
    思路：
        1.写出交换代码
        2.将数组中随机位置的元素与末尾元素交换
        3.对数组进行荷兰国旗问题处理，即以最右元素为标准，小于放左边，等于放中间，大于放右边，返回中间的两侧下标
        4.递归处理小于组和大于组
    易错点：
        1.base case处理不严谨
        2.外部包装错误，最外层入参只有一个数组
        3.没有先处理输入异常和base case
    */

    // 交换
    public static void swap(int[] arr, int n,int m) {
        int tmp = arr[n];
        arr[n] = arr[m];
        arr[m] = tmp;
    }

    // 荷兰国旗问题
    public static int[] netherlandsFlag(int[] arr, int L, int R) {
        // wrong case
        if (L > R) {
            return new int[]{-1, -1};
        }
        // base case
        if (L == R) {
            return new int[] {L, L};
        }

        //返回等于数组左右下标
        int idx = L;
        int less = L - 1;
        int more = R;
        while (idx < more) {
            if (arr[idx] < arr[R]) {
                swap(arr, idx++, ++less);
            } else if (arr[idx] == arr[R]) {
                idx++;
            } else if (arr[idx] > arr[R]) {
                swap(arr, idx, --more);
            }
        }
        swap(arr, more++, R);
        return new int[] {less + 1, more - 1} ;
    }

    // 递归处理
    public static void partition(int[] arr, int L, int R) {
        // base case
        if (L > R || L == R) {
            return;
        }
        // 随机交换元素
        swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
        // 对当前数组进行partion
        int [] equal = netherlandsFlag(arr, L, R);
        // 对左右两组递归partion
        partition(arr, L, equal[0] - 1);
        partition(arr, equal[1] + 1, R);
    }

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        partition(arr, 0, arr.length - 1);
    }





    //////////////////////////////////////////////////////////////////////////////////////////////////////
/*    public static void main(String[] args) {
        int[] test = {5, 4, 3, 2, 1};
        quickSort(test);
        System.out.println(Arrays.toString(test));
    }*/

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
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

    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process2(arr, 0, arr.length - 1);
    }

    // arr[L...R] 排有序，快排2.0方式
    public static void process2(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        // [ equalArea[0]  ,  equalArea[0]]
        int[] equalArea = netherlandsFlag(arr, L, R);
        process2(arr, L, equalArea[0] - 1);
        process2(arr, equalArea[1] + 1, R);
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 5;
        int maxValue = 10;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            quickSort2(arr1);
            quickSort(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "通过测试!" : "未通过测试!");

    }

}
