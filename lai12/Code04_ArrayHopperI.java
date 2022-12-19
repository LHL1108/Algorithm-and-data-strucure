package lai12;
/*
[Question]
    give an array, jump from 0 idx, we can jump arr[i] step, return if we can jump to the tail of arr
[Idea]
    jump each step, update the furthest position we can jump, if the next step is in this position, we update max and jump
    until our max reach the end or cur step is 0, which mean we can no longer update max
    a = {2, 1, 1, 0, 2}
    f[0] = 0 + 2 = 2 max = 2
    f[1] = 1 + 1 = 2 max = 2
    f[2] = 2 + 1 = 3 max = 3
    f[3] = 3 + 0 = 3 max = 3
Time : O(n)
Space: O(1)
*/

public class Code04_ArrayHopperI {
    public static boolean canJump(int[] arr){
        int i = 0; //当前站的位置
        int max = arr[i]; //最远能跳哪里
        while (i <= max) { //可以继续往前跳
            max = Math.max(max, i + arr[i]); //max更新
            if (max >= arr.length - 1) {
                return true; //可以跳
            } else if (i == max && arr[i] == 0){
                return false;
            } else {
                i++;
            }
        }
        return false;
    }



    public static void main(String[] args) {
        int[] arr = {1, 2, 0, 8, 1, 2};
        //false
        System.out.println(canJump(arr));
    }
}
