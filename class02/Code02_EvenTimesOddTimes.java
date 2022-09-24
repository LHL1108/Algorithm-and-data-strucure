package class02;

public class Code02_EvenTimesOddTimes {
    // arr中，只有一种数，出现奇数次
    public static int printOddTimesNum1(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        return eor;
    }

    // arr中，只有2种数，出现奇数次
    /*
    * 两个数肯定不等，所以某一位上一定一个是0一个是1
    * 提取最右侧的1
    * 把所有最右侧是1的与出来一个数 第一个数
    *
    * */
    public static void printOddTimesNum2(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        int rightOne = eor & (-eor);
        int eor2 = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & rightOne) != 0) {
                eor2 ^= arr[i];
            }
        }
        int firstOne = eor2;
        int secondOne = eor ^ eor2;
        System.out.println(firstOne + " , "+ secondOne);
    }


    public static void main(String[] args) {
/*
        int[] test1 = {2, 2, 4, 1, 1};
        int ans = printOddTimesNum1(test1);
        System.out.println(ans);
*/
        int[] test1 = {2, 2, 4, 1, 1, 5, 5, 9};
        printOddTimesNum2(test1);

    }

}