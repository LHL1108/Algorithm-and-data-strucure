package class04;

import java.util.Arrays;

public class Code01_MergeSort {
    //递归版归并排序

    //创建函数
    public static void mergeSort(int[] arr) {
        // 不需要排序
        if (arr == null || arr.length < 2){
            return;
        }
        // 需要排序,递归版归并
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int L, int R) {
        // base case
        if (L == R) {
            return;
        }
        int M = L + ((R - L) >> 1);
        // 处理左半部分
        process(arr, L, M);
        // 处理右半部分
        process(arr, M + 1, R);
        // 融合两部分
        merge(arr, L, M, R);
    }

    public static void merge(int[] arr, int L, int M, int R) {
        // 触界前
        int[] help = new int[R - L + 1];
        // 两组各一个指针，谁小拷贝谁
        int left = L;
        int right = M + 1;
        int idx = 0;
        while(left < M + 1 && right < R + 1){
            help[idx++] = arr[left] <= arr[right] ? arr[left++] : arr[right++];
        }
        // 触界后
        while(left < M + 1){
            help[idx++] = arr[left++];
        }
        while(right < R + 1){
            help[idx++] = arr[right++];
        }
        //拷贝数组
        for (int i = 0; i < R - L + 1; i++) {
            arr[L + i] = help[i];
        }
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

    // 非递归方法实现
    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        // 步长
        int mergeSize = 1;
        while (mergeSize < N) { // log N
            // 当前左组的，第一个位置
            int L = 0;
            while (L < N) {
                if (mergeSize >= N - L) {
                    break;
                }
                int M = L + mergeSize - 1;
                int R = M + Math.min(mergeSize, N - M - 1);
                merge(arr, L, M, R);
                L = R + 1;
            }
            // 防止溢出
            if (mergeSize > N / 2) {
                break;
            }
            mergeSize <<= 1;
        }
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
        int testTime = 500000;
        int maxSize = 50;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            mergeSort(arr1);
            mergeSort2(arr2);
            if (!isEqual(arr1, arr2)) {
                System.out.println("出错了！");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束,无异常");
    }
//    public static void main(String[] args) {
//        int test[] = {-15, -14, -6, 58};
//        System.out.println(Arrays.toString(test));
//        mergeSort(test);
//        System.out.println(Arrays.toString(test));
//    }
}
