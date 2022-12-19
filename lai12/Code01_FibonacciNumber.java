package lai12;
/*
[Question]
    solve the fibonacci number problem with DP method
[Idea]
    fill up a form， use former two number to calculate the next number, finally return the last number
        0 -> 0
        1 -> 1
    [0, 1, 1, 2]
[Complexity]
    Time: O(n)
    Space:O(1)
[Notice]
    feel the feeling of dp, fill the form
    if no need to use all the past data, we can reduce the space complexity
*/

public class Code01_FibonacciNumber {

    public static long fibonacci(int K) {
        if (K == 0) {
            return 0;
        }
        if (K == 1) {
            return 1;
        }
        long[] arr = new long[2];
        arr[0] = 0;
        arr[1] = 1;
        long tmp;
        for (int idx = 1; idx < K; idx++){
            tmp = arr[0] + arr[1];
            arr[0] = arr[1];
            arr[1] = tmp;
        }
        return arr[1];
    }

    public static void main(String[] args) {
        System.out.println(fibonacci(4));
    }
}
