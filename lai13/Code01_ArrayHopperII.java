package lai13;
/*
[Question]
    min step of jumping from head to tail in an array, if can not, return -1
    {3, 3, 1, 0, 4}, return 2
[Idea]
    for each position, jump directly or from middle, so 1 or valid smallest mid step + 1
    valid means we can jump to the middle(not -1), and mid can jump to cur
[Notice]
    we need to update globalMin to MAX_VALUE each round
    remember, only the valid mid should be considered
    don't forget directly situation
    f[1] is not always 1, could be -1
    don't forget 1 element situation
[Complexity]
    Time: O(n^2) to traverse
    Space: O(n), f[]
*/

public class Code01_ArrayHopperII {
    public static int minJump(int[] arr) {
        if (arr.length <= 1) {
            return 0;
        }

        int n = arr.length;
        int[] f = new int[n];
        f[1] = arr[0] == 0 ? -1 : 1;

        for (int cur = 2; cur <= n - 1; cur++) {
            if (arr[0] >= cur) {
                f[cur] = 1;
            } else {
                int globalMin = Integer.MAX_VALUE;
                for (int mid = cur - 1; mid >= 1; mid--) {
                    if (f[mid] != -1 && arr[mid] >= cur - mid) {
                        globalMin = Math.min(f[mid], globalMin);
                    }
                }
                f[cur] = globalMin == Integer.MAX_VALUE ? -1 : globalMin + 1;
            }
        }
        return f[n-1];
    }



    public static int minJump2(int[] arr) {
        if (arr.length <= 1) {
            return 0;
        }
        int n = arr.length;
        int[] minJump = new int[n];
        for(int i = n - 1; i >= 0; i--) {
            int min = Integer.MAX_VALUE;
            if (i + arr[i] >= n - 1) {
                minJump[i] = 1;
            } else {
                for (int j = i + 1; j <= i + arr[i]; j++) {
                    if (minJump[j] == -1) {
                        continue;
                    } else {
                        min = Math.min(min, minJump[j]);
                    }
                }
                minJump[i] = min == Integer.MAX_VALUE ? -1 : min + 1;
            }
        }
        return minJump[0];
    }

    public static void main(String[] args) {
        int[] arr = {2,1,0,3,1};
        System.out.println(minJump2(arr));
    }
}
