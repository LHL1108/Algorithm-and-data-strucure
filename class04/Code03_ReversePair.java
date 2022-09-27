package class04;

public class Code03_ReversePair {
    /*
    要求：求数组中的逆序对个数，逆序对定义，左边数大于右边数，如[7,2,3,4]中的[7,2],[7,4]等
    思路：归并排序递归计算左右两部分的逆序对数量，最终求merge过程中的整个数量
         从右往左依次放入help数组
         如果出现逆序对，则一并计算右组数量
    */
    public static int reverPairNumber(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int L, int R) {
        if(L == R) {
            return 0;
        }
        int M = L + ((R - L) / 2);
        return process(arr, L, M) + process(arr, M + 1, R) + merge(arr, L, M, R);
    }

    public static int merge(int[] arr, int L, int M, int R) {
        int p1 = M;
        int p2 = R;
        int res = 0;
        int help[] = new int[R - L + 1];
        int idx = R - L;

        while (p1 >= L && p2 >= M + 1) {
            // 计算逆序对个数
            res += arr[p1] > arr[p2] ? (p2 - M): 0;
            help[idx--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
        }
        while (p1 >= L) {
            help[idx--] = arr[p1--];
        }
        while (p2 >= M+1) {
            help[idx--] = arr[p2--];
        }

        for (int i = 0; i < R - L + 1; i++) {
            arr[L + i] = help[i];
        }

        return res;
    }

   //////////////////////////////////////////////////////////////////////////////////
  /*
   public static void main(String[] args) {
       int[] test = {3, 2, 1};
       int res = reverPairNumber(test);
       System.out.println(res);
   }
   */

    // for test
    public static int comparator(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    ans++;
                }
            }
        }
        return ans;
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
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (reverPairNumber(arr1) != comparator(arr2)) {
                System.out.println("Oops!");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试通过");
    }

}
