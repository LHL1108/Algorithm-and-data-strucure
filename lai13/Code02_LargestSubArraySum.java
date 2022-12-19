package lai13;
/*
[Question]
    find the largest sub array sum in the array
    {2, -1, 4, -2, 1}, return 5, (2, -1, 4)
[Idea]
    sub array must end as some element, so f[i] means the largest sum end with arr[i],
    the relation is, if a[i] <= 0, the f[i+1] no need to follow it and should be arr[i+1], other wise follow it, should be f[i] + arr[i+1]
    finally return global max
    {2, -1, 4, -2, 1}

    f[0] = 2, max = 2
    f[1] = 1, max = 2
    f[2] = 5, max = 5
    f[3] = 3, max = 5
    f[4] = 4, max = 5
    return 5
[Complexity]
    Time:  O(n)
    Space: O(1)
*/


public class Code02_LargestSubArraySum {

    public static int largestSum(int[] arr) {
        if (arr.length == 1) {
            return arr[0];
        }
        int[] dp = new int[2];
        dp[0] = arr[0];
        int max = dp[0];
        for (int i = 1; i < arr.length; i++) {
            if (dp[0] <= 0) {
                dp[1] = arr[i];
            } else {
                dp[1] = dp[0] + arr[i];
            }
            max = Math.max(max, dp[1]);
            dp[0] = dp[1];
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {2, -1, 4, -2, 1};
        int res = largestSum(arr);
        System.out.println(res);
    }
}
