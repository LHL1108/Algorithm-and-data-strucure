package lai14;
/*
[Question]
    find the length of the longest consecutive sub array of 1s
[Idea]
    use dp, longest 1 sub array end in n,if current number is 1, add 1 to previous result or use previous result, then update global max, finally return global max
    {0, 1, 0, 1, 1, 1, 0},
    dp[0] = 0, max = 0
    dp[1] = dp[0] + 1 max = 1
    dp[2] = 0
    dp[n] = arr[n] == 1 ? dp[n - 1] + 1 : dp[n - 1], update max
[Complexity]
    Time: O(n)
    Space: O(n)
[Notice]
    when arr[i] != 1, we should set dp[i] to 0, because it is not consecutive
    Don't forget judge input
    set max to 0, not MIN_VALUE at first
    update max at initial state
    don't consider i = 0 when we set dp[0]
*/

public class Code01_LongestConsecutive1s {

    public static int longest(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = 0;
        int[] dp = new int[arr.length];
        dp[0] = arr[0] == 1 ? 1 : 0;
        max = Math.max(max, dp[0]);
        for (int i = 1; i < arr.length; i++) { //bug
            if (arr[i] == 1) {
                dp[i] = dp[i - 1] + 1;
            } else {
                dp[i] = 0;
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }


    public static void main(String[] args) {
        int[] arr = {0, 1, 0, 1, 1, 1, 0};
        // 3
        System.out.println(longest(arr));
    }
}
