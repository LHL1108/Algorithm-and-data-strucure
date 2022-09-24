package class02;

public class Code01_Swap {

    public static void main(String[] args) {
        int a = 100;
        int b = 200;

        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println(a + " , " + b);
    }


}
