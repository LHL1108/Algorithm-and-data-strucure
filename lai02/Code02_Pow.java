package lai02;
/*
[Question]
    Implement an exponential function method a^b, ignore corner case, assume 0^0 = 1
[Idea]
    use recursion, because we can split large scale exp problem into two small scale exp problems
[Construction]
    base case
        when b is 1, return a
    recursive rule
        when b is even
            multiple two a^(b/2)
        when b is odd
            a * multiple two a^(b/2)
[Notice]
    calculate in advance to avoid to many recursion
    consider to use long rather than int when involve large number problem
[Complexity]
    Time: logN, halved each time
    Space: logN, halved each time
*/

public class Code02_Pow {



    public static long pow(int a, int b) {
        if (b == 0) {
            return 1;
        }
        if (b == 1) {
            return a;
        }

        long half = pow(a, b/2);
        return b % 2 == 0 ? half * half : a * half * half;
    }



    public static void main(String[] args) {
        int a = 0;
        int b = 0;

        long res = pow(a, b);
        System.out.println(res);
    }
}
