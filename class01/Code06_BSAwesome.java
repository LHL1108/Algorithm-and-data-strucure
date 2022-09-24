package class01;

public class Code06_BSAwesome {
    public static int getLessIndex(int[] arr) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        if (arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }
        int L = 0;
        int R = arr.length -1;
        while (L < R) {
            int mid = L + (R - L) / 2;
            if (arr[mid] > arr[mid + 1]) {
                L = mid;
            } else if (arr[mid] > arr[mid - 1]) {
                R = mid;
            }else {
                return mid;
            }
        }
        return L;
    }


    public static void main(String[] args) {
        int[] test1 = {-1, 2, 1, 0, -1};
        int ans = getLessIndex(test1);
        System.out.println(ans);
    }
}

/*
1.判空
2.看左边
3.看右边
4.看中间二分递归*/
