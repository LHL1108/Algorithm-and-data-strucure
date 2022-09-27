package class04;

public class Code02_SmallSum {
    /*
    要求：对于一个数组例如，[1,2,3,4]。其中1和2,3,4都会产生小和1，同理2与3,4也都会产生小和2，依次累加所有的小和然后返回。
    思路: 归并排序，在merge的过程中放左数前计算一遍小和，放右数则不计算
    易错点： 归并排序注意递归时的base case，否则易出现死循环

    */
    public static int smallSum(int[] arr) {
        if (arr == null || arr.length <= 1){
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int L, int R) {
        if (L == R){
            return 0;
        }
        int M = L + ((R - L) / 2);
        return process(arr, L, M) + process(arr, M + 1, R) + merge(arr, L, M, R);
    }

    public static int merge(int[] arr, int L, int M, int R) {
        /*
        遍历左数组，对于每一个左数，批量计算该左数小和
        创建help数组，左右各一指针，谁小记录谁，一组到头后复制另一组剩余数组
        */

        int res = 0;
        int p1 = L;
        int p2 = M + 1;
        int idx = 0;
        int[] help = new int[R - L + 1];

        while (p1 <= M && p2 <= R) {
            // 这里计算小和
            res += arr[p1] < arr[p2] ? arr[p1] * (R - p2 + 1) : 0;
            help[idx++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M) {
            help[idx++] = arr[p1++];
        }
        while (p2 <= R) {
            help[idx++] = arr[p2++];
        }
        for (int i = 0; i < R - L + 1; i++) {
            arr[L + i] = help[i];
        }
        return res;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////
/*    public static void main(String[] args) {
        int[] test = {1, 2, 3, 4};
        int res = smallSum(test);
        System.out.println(res);
    }*/
    // for test
    public static int comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int res = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                res += arr[j] < arr[i] ? arr[j] : 0;
            }
        }
        return res;
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
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (smallSum(arr1) != comparator(arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "测试通过" : "测试失败!");

    }

}
