package class02;

import java.util.HashMap;

public class Code03_KM {

    // 编写测试方法，使用哈希表，统计数组中每个数出现的次数
    // 遍历哈希表索引，返回出现K次的数据
    public static int test(int[] arr, int k) {
        // 构建哈希表
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            //如果这个数在哈希表中不存在，则创建该数字作为索引，值为1
            if (!map.containsKey(num)){
                map.put(num, 1);
            }else{
                //如果这个数在哈希表中存在，则将该数字作为索引的值+1
                map.put(num, map.get(num)+1);
            }
        }
        for (int num : map.keySet()) {
            if (map.get(num) == k){
                return num;
            }
        }
        return -1;
    }

    public static HashMap<Integer, Integer> map = new HashMap<>();

    public static void getMap() {
        int val = 1;
        for (int i = 0; i < 32; i++) {
            map.put(val, i);
            val <<= 1;
        }
    }

    public static int KM(int[] arr, int k, int m) {
        int[] sum = new int[32];
        if (map.size() == 0) {
            getMap();
        }
        for (int num : arr) {
            while (num != 0) {
                int rightOne = num & (-num);
                sum[map.get(rightOne)]++;
                num ^= rightOne;
            }

        }
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if ((sum[i] % m) != 0 && (sum[i] % m) == k) {
                ans |= (1 << i);
            }
        }

        if (ans == 0) {
            int times = 0;
            for(int num : arr) {
                if (num == 0) {
                    times++;
                }
            }
            if (times == k) {
                return ans;
            } else {
                return -1;
            }
        }

        return ans;
    }
/*
    产生随机数组
    通过数字种数t范围，数值范围range, k,m范围产生随机数组
            同步运行两种方法
    比对
*/

    public static int getKM(int kmRange) {
        int KorM = (int) (Math.random() * kmRange) + 1; //[1, R]
        return KorM;
    }

    public static int getNum(int numRange, HashMap<Integer, Integer> map) {
        int num = (int) (Math.random() * (numRange + 1));
        while (map.containsKey(num)) {
            num = (int) (Math.random() * (numRange + 1));
        };
        map.put(num, 1);
        return num;
    }

    public static int[] generateRandomArray(int numKinds, int numRange, int k, int m) {

        int[] arr = new int[k + (numKinds - 1) * m];
        int idx = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numKinds; i++) {
            int addNum = getNum(numRange, map);
            int addTimes = 0;
            if(i == 0) {
                addTimes = k;
            } else {
                addTimes = m;
            }
            for (int j = 0; j < addTimes; j++) {
                arr[idx] = addNum;
                idx++;
            }
        }

        // 打乱arr: 对于每个数，将其和i位置的数交换 [0,len-1]
        for (int i = 0; i < arr.length; i++) {
            int j = (int) (Math.random() * arr.length);
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
        return arr;
    }

    public static int[] copyArr(int[] arr1) {
        int[] arr2 = new int[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            arr2[i] = arr1[i];
        }
        return arr2;
    }
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + ", ");
        }
        System.out.println();
    }

    public static boolean compareAns(int[] arr1, int[] arr2) {
        if (arr1 == null && arr2 != null) {
            return false;
        }else if(arr1 != null && arr2 == null) {
            return false;
        }else if(arr1.length != arr2.length){
            return false;
        }else{
            for (int i = 0; i < arr1.length; i++) {
                if (arr1[i] != arr2[i]) {
                    return false;
                }
            }
        }
        return true;
    }



    public static void main(String[] args) {
/*
        int[] test1 = {1, 1, 2, 2, 3, 3, 4};
        int k = 1;
        int num = test(test1, k);
        System.out.println(num);
        */
/*
        // 题目要求： 数组中只有一种数出现了k次，其他数都出现m次，找出这种数
        // 示例： arr = {1， 1， 2， 2， 3}, k = 1, m = 2     return 3;
        // 方法：
            遍历每个数，以二进制的形式加入空数组中
            将数组中每个数模上m,观察哪些位置等于k
            将这些数还原
*/
        /*
        int[] test1 = {1, 1, 1, 2, 2, 2, 3, 3, 5, 5, 5};
        int k = 2;
        int m = 3;
        int ans = KM(test1, k, m);
        System.out.println(ans);
        */
        int testTimes = 100;
        for (int i = 0; i < testTimes; i++) {
            int numKinds = 5;
            int numRange = 10;
            int kmRange = 4;
            int k = 0;
            int m = 0;
            while (k >= m) {
                k = getKM(kmRange);
                m = getKM(kmRange);
            }

            int[]arr1 = generateRandomArray(numKinds, numRange, k, m);
            int[]arr2 = copyArr(arr1);

            int ans1 = KM(arr1, k, m);
            int ans2 = test(arr2, k);

            if (ans1 != ans2) {
                System.out.println("Wrong!");
                printArray(arr1);
                System.out.println("My Answer: " + ans1);
                System.out.println("Standard Answer: " + ans2);
                break;
            }

            if (i == (testTimes - 1)) {
                System.out.println("Nice!");
                printArray(arr1);
                System.out.println("My Answer: " + ans1);
                System.out.println("Standard Answer: " + ans2);
            }
        }


    }
}

