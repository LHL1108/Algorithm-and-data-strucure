package class06;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Code03_HeapSort {
    /*
    要求： 写出堆排序并说明解释时间及空间复杂度
    思路：
        1.对一个数组从左至右进行一轮heapInsert,将其调整成大根堆，于是左边获得数组最大值
        2.将其与最右交换，获得排序后最大值(位于最右)
        3.调整堆的大小将其断连，对新堆顶进行heapify，重新获得最大值，并与新堆底交换
        4.循环处理，直至堆仅剩最后一个元素
        5.边界判断
    时间复杂度：heapInsert O(N) ， heapify环节对N个元素做了heapify操作，近似Nlogn,所以总体取大 Nlogn
    空间复杂度: 依靠原数组排序，仅使用有限几个变量，所以空间复杂度是1
    */


    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void heapInsert(int[] heap, int idx) {
        /*
        算出爹
        只要比爹的值大
            和爹交换元素
            变更下表
            重新计算爹
        */
        int father = (idx - 1) / 2;
        while (heap[idx] > heap[father]) {
            swap(heap, idx, father);
            idx = father;
            father = (idx - 1) / 2;
        }
    }

    public static void heapify(int[] heap, int idx, int size) {
        int left = (2 * idx) + 1;
        while (left <= size - 1) {
            int largerIdx = (left + 1) <= size -1 && heap[left + 1] > heap[left]? left + 1 : left;
            largerIdx = heap[largerIdx] > heap[idx] ? largerIdx : idx;
            if (largerIdx == idx) {
                break;
            }
            swap(heap, idx, largerIdx);
            idx = largerIdx;
            left = (2 * idx) + 1;
        }
    }

    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int size = arr.length;
        //对一个数组从左至右进行一轮heapInsert,将其调整成大根堆，于是左边获得数组最大值
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }
        // 循环处理，直至堆仅剩最后一个元素
        while (size > 1){
            //将其与最右交换，获得排序后最大值(位于最右)
            swap(arr, 0, size - 1);
            //调整堆的大小将其断连，对新堆顶进行heapify，重新获得最大值，并与新堆底交换
            size--;
            heapify(arr, 0, size);
        }
    }



//////////////////////////////////////////////////////////////////
/*    public static void main(String[] args) {
        int[] test = {4,8,9,2,5,3,1,7};
        heapSort(test);
        System.out.println(Arrays.toString(test));
        // except [1, 2, 3, 4, 5, 7, 8, 9] (升序排序)
    }*/

    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
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

        int testTime = 500;
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            heapSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "测试通过！" : "测试失败");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        heapSort(arr);
        printArray(arr);
    }
}
