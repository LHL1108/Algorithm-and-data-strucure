package class06;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Code04_SortArrayDistanceLessK {
    /*
    要求：一个数组，每个数距离它正确的位置小于k，请将其正确升序排序
    思路：
        1.利用距离正确位置小于k的信息，则在数组中构建一个长度为K的数组，则最小值必在这个子数组中
        2.将该子数组调整为小顶堆，则堆顶数字必然是正确数字
        3.三个状态，刚开始时堆大小逐渐增大，中间保持不变，在滑窗移动至最右后，堆的大小逐渐减小至0
    易错：
        1.注意k大于数组长度的情况
        2.注意k和堆大小的关系，举例，k=1时构建大小为2的堆
    */
    public static void sortedArrayDistanceLessK(int[] arr, int k) {
        // 异常判断，为空，小于2，无需处理
        if (arr == null || arr.length < 2) {
            return;
        }
        // 初始化数据
        PriorityQueue<Integer>heap =  new PriorityQueue<Integer>(k);
        int t = 0;
        int x = 0;
        int heapSize = k + 1;
        if (k >= arr.length) {
            heapSize = arr.length;
        }
        // 第一个状态，堆的大小逐渐增长至K,则首位元素调整至正确位置
        for (int i = 0; i < heapSize; i++) {
            heap.add(arr[t++]);
        }
        arr[x++] = heap.poll();

        // 第二个状态，滑窗向右移动len-k次
        for (int i = 0; i < arr.length - heapSize; i++) {
            heap.add(arr[t++]);
            arr[x++] = heap.poll();
        }

        // 第三个状态，滑窗缩小k-1次
        for (int i = 0; i < heapSize - 1; i++) {
            arr[x++] = heap.poll();
        }
    }

//////////////////////////////////////////////////////////////////////////////////////////////
/*    public static void main(String[] args) {
        int[] test = {3, 1, 2, 6, 4, 7, 5};
        int k = 3;
        sortedArrayDistanceLessK(test, k);
        System.out.println(Arrays.toString(test));
    }*/



    // for test
    public static void comparator(int[] arr, int k) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] randomArrayNoMoveMoreK(int maxSize, int maxValue, int K) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        // 先排个序
        Arrays.sort(arr);
        // 然后开始随意交换，但是保证每个数距离不超过K
        // swap[i] == true, 表示i位置已经参与过交换
        // swap[i] == false, 表示i位置没有参与过交换
        boolean[] isSwap = new boolean[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int j = Math.min(i + (int) (Math.random() * (K + 1)), arr.length - 1);
            if (!isSwap[i] && !isSwap[j]) {
                isSwap[i] = true;
                isSwap[j] = true;
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
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
        int testTime = 500000;
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int k = (int) (Math.random() * maxSize) + 1;
            int[] arr = randomArrayNoMoveMoreK(maxSize, maxValue, k);
            int[] arr1 = copyArray(arr);
            int[] arr2 = copyArray(arr);
            sortedArrayDistanceLessK(arr1, k);
            comparator(arr2, k);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                System.out.println("K : " + k);
                printArray(arr);
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "测试通过!" : "测试未通过!");
    }
}
