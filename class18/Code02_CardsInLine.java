package class18;
/*
要求： 在一个arr数组中，每个值相当于一组牌的点数，有两个玩家，分别先手和后手从两边拿牌，两个人都绝顶聪明，会是自己的点数和最大化，求获胜者的分数。
思路：
    1.在拿牌的过程中，不断重复的就是在arr的特定位置先手或后手拿使自己将获得最终最大点数和的牌,basecase是只剩一张的时候
    2.因为两个递归有重复调用的过程，所以将f和l值分别记录在两个[len, len]的二维数组中，如果遇到重复的直接取
    3.根据计算公式推出或通过表格发现规律，直接把所有可能的结果算好提前放在表中，所有情况都可以直接取。
*/

public class Code02_CardsInLine {

    public static int win1(int[] arr) {
        if (arr.length <= 0 || arr == null) {
            return 0;
        }
        return Math.max(f(arr, 0, arr.length-1), l(arr, 0, arr.length-1));
    }

    public static int f(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        return Math.max(arr[L] + l(arr, L+1, R), arr[R] + l(arr, L, R-1));
    }

    public static int l(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        return Math.min(f(arr, L+1, R), f(arr, L, R-1));
    }




    public static int win2(int[] arr) {
        if (arr.length <= 0 || arr == null) {
            return 0;
        }
        int N = arr.length;
        int[][] fmap = new int[N][N];
        int[][] lmap = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                fmap[i][j] = -1;
                lmap[i][j] = -1;
            }
        }
        return Math.max(f2(arr, 0, arr.length-1, fmap, lmap), l2(arr, 0, arr.length-1, fmap, lmap));
    }

    public static int f2(int[] arr, int L, int R, int[][] fmap, int[][] lmap) {
        if (fmap[L][R] != -1) {
            return fmap[L][R];
        } else {
            int ans = 0;
            if (L == R) {
                ans = arr[L];
            } else {
                ans = Math.max(arr[L] + l2(arr, L+1, R, fmap, lmap), arr[R] + l2(arr, L, R-1, fmap, lmap));
            }
            fmap[L][R] = ans;
            return ans;
        }
    }

    public static int l2(int[] arr, int L, int R, int[][] fmap, int[][] lmap) {
        if (lmap[L][R] != -1) {
            return lmap[L][R];
        } else {
            int ans = 0;
            if (L == R) {
                ans = 0;
            } else {
                ans = Math.min(f2(arr, L+1, R, fmap, lmap), f2(arr, L, R-1, fmap, lmap));
            }
            lmap[L][R] = ans;
            return ans;
        }
    }

    public static int win3(int[] arr) {
        if (arr.length == 0 || arr == null) {
            return 0;
        }
        int N = arr.length;
        int[][] fmap = new int[N][N];
        int[][] lmap = new int[N][N];
        for (int i = 0; i < N; i++) {
            fmap[i][i] = arr[i];
            lmap[i][i] = 0;
        }

        for (int t = 1; t < N; t++) {
            int l = 0;
            int r = t;
            while(r < N) {
                fmap[l][r] = Math.max(arr[l] + lmap[l+1][r], arr[r] + lmap[l][r-1]);
                lmap[l][r] = Math.min(fmap[l+1][r], fmap[l][r-1]);
                l++;
                r++;
            }
        }
        return Math.max(fmap[0][N-1], lmap[0][N-1]);

    }


    public static void main(String[] args) {

        int[] arr = { 5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7 };  // expect 32
        //int[] arr = {10, 100, 10};  // expect 100
        System.out.println(win1(arr));
        System.out.println(win2(arr));
        System.out.println(win3(arr));
    }

}
