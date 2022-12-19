package class17;
/*
要求： 请把有一个n层的汉诺塔从最左移动到最右（汉诺塔只能保持大的再下，小的在上的状态）
思路： 如果只有1层，那么直接将该层从from移动到to,打印
      如果不止一层，那么先将1至n-1由from移动到other
      再将最后一层，直接由from移动到to,打印
      最后将1至n-1层由other移动到to
*/

public class Code01_Hanoi {

    public static void Hanoi(int n){
        func(n, "左", "右", "中");
    }

    public static void func(int n, String from, String to, String other) {
        if (n == 1) {
            System.out.println("将1由" + from + "移动至" + to);
        } else {
            func(n-1, from, other, to);
            System.out.println("将" + n + "由" + from + "移动至" + to);
            func(n-1, other, to, from);
        }
    }

    public static void main(String[] args) {
        Hanoi(3);
    }
}
