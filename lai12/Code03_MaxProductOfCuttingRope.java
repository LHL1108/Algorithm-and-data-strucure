package lai12;

public class Code03_MaxProductOfCuttingRope {

    public static int maxProduct(int n) {
        int[] products = new int[n + 1];
        products[0] = 0;
        products[1] = 1;
        products[2] = 1;
        int max = 1;
        for (int i = 3; i <= n; i++) {
            for(int j = i - 1; j >= 1; j--) {
                int cur = Math.max(j, products[j]) * (i - j);
                max = Math.max(cur, max);
            }
            products[i] = max;
        }
        return products[n];
    }

    public static void main(String[] args) {
        System.out.println(maxProduct(12));
    }
}
