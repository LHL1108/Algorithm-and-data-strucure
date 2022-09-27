package class04;

public class Code04_BiggerThanTwice {
    /*
    要求： 求数组中有多少个子数组，左数大于右数的两倍，如[6,2,1]中的[6,2]
    思路：对每个左数，构建滑窗，只要右数小于左数两倍就向优化，构建不回退指针批量计算
    易错：注意批量求结果时相减的边界条件
     */
    public static int biggerTwice(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int L, int R) {
        //base case
        if (L == R) {
            return 0;
        }
        int M = L + ((R - L) / 2);
        return process(arr, L, M) + process(arr, M + 1, R) + merge(arr, L, M, R);
    }

    public static int merge(int[] arr, int L, int M, int R) {
        //统计数量
        int windowR = M + 1;
        int res = 0;
        int p1 = L;
        int p2 = M + 1;
        int idx = 0;
        int[] help = new int[R - L + 1];
        for (int i = L; i <= M; i++) {
            while (windowR <= R && arr[i] > (2 * arr[windowR])){
                windowR++;
            }
            res += (windowR - 1) - M;
        }
        //merge
        while (p1 <= M && p2 <= R) {
            help[idx++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }

        while (p1 <= M) {
            help[idx++] = arr[p1++];
        }
        while (p2 <= R) {
            help[idx++] = arr[p2++];
        }
        for (int i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return res;
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////
   /*
    public static void main(String[] args) {
        int[] test = {6, 5, 2};  // return 2
        int res = biggerTwice(test);
        System.out.println(res);
    }
*/
    // for test
    public static int comparator(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > (arr[j] << 1)) {
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
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) ((maxValue + 1) * Math.random());
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
            if (biggerTwice(arr1) != comparator(arr2)) {
                System.out.println("Oops!");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试通过");
    }
}
