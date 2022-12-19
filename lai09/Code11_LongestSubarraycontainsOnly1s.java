package lai09;
/*
[Question]
    we can change 0 to 1 k times, find the longest length of substring with all 1
[Idea]
    never ignore any chance, find each possible substring and update length
    if j is 1, just extend its length, if j is 0, we need to fill 0 as possible, other wise move i
    so i is the beginner, j is the possible longest tail, without k means we couldn't extend it anymore, so we can only move i
    to get a new substring
[Notice]
    2 pointers, i the head of substring, j is possible tail is a method
    know the meaning of the pointer
    if cnt >= k, move slow first
[Complexity]
    Time:   O(n), traverse
    Space:  O(1), several variables
*/

public class Code11_LongestSubarraycontainsOnly1s {

    public static int longestConsecutiveOnes(int[] nums, int k) {
        int slow = 0;
        int fast = 0;
        int longest = 0;
        int cnt = 0;
        while (fast < nums.length) {
            if (nums[fast] == 1) {
                longest = Math.max(longest, ++fast - slow);
            } else if (cnt < k) {
                cnt++;
                longest = Math.max(longest, ++fast - slow);
            } else if (nums[slow++] == 0) {
                cnt--;
            }
        }
        return longest;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,0,0,1,1,1,0,0,0};
        int k = 2;
        int res = longestConsecutiveOnes(nums, k);
        System.out.println(res);
    }
}
