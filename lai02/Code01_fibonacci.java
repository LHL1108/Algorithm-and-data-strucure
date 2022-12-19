package lai02;
/*
[Question]
    to implement a fibonacci function, example 1,1,2,3,4, when k <= 0, return 0
[Idea]
    use recursion, fib(k) = fib(k - 1) + fib(k - 2)
[Construction]
    cleaning check
        k <=0
    base case
        k = 1, 2
    recursive rule
        from 3, fib(k-1) + fib(k-2)
[Complexity]
    Time:  2^N, because the depth of the tree is N, so there are 2^N nodes, each node O(1), so O(2^N) in total
    Space: N, because the depth of the tree is N, each node O(1), so O(N) in total
*/


public class Code01_fibonacci {

    public static int fibonacci(int K) {
        if (K <= 0) {
            return 0;
        }

        if (K == 1 || K == 2) {
            return 1;
        }

        return fibonacci(K - 1) + fibonacci(K - 2);
    }


    public static void main(String[] args) {
        int K = 5;

        int res = fibonacci(K);
        System.out.println(res); //5
    }
}
