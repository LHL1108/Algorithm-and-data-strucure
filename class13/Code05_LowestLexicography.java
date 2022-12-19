package class13;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;
/*
要求： 给定一个字符串数组，返回字典序最小的全排列字符串
思路：
    暴力方法：全排列出所有顺序后返回放入Treeset中，返回第一个
    贪心方法：将数组根据两两组合的方式贪心排序，得到结果

代码思路：
    贪心
        排序方法
            两两拼接，字典序小的放前面

        主方法
            null或长度为0
                返回""
            对数组进行排序
            获得结果
                遍历每一个数据，拼接进结果
            返回结果

     暴力
        判空
            null
            0
            返回""
        处理
            返回TreeSet<String>
        返回
            大小为0
                ""
            大小不为0
                返回第一个

        处理算法
            初始化
            输入为空的情况
            输入不为空的情况
                遍历每一个字符
                    将该字符抓取为首位字符
                    获得后续字符
                    搞出后续字符的全排列结果
                    将抓取首位字符和每一个全排列结果进行拼接并塞入结果
            返回结果

        取出字符穿中的某位
            初始化
            遍历每一个字符
                只要不是目标为
                就加入
*/


public class Code05_LowestLexicography {


    public static class MyComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b){
            return (a + b).compareTo(b + a);
        }
    }

    public static String lowestString2(String[] str) {
        if (str == null || str.length == 0) {
            return "";
        }
        Arrays.sort(str, new MyComparator());
        String res = "";
        for (int i = 0; i < str.length; i++) {
            res += str[i];
        }
        return res;
    }

    public static String lowestString1(String[] str) {
        if (str == null || str.length == 0) {
            return "";
        }
        TreeSet<String> ans = new TreeSet<>();
        ans = process(str);
        return ans.size() == 0 ? "" : ans.first();
    }

    public static TreeSet<String> process(String[] str){
        TreeSet<String> res = new TreeSet<>();
        if (str == null || str.length == 0) {
            res.add("");
            return res;
        }

        for (int i = 0; i < str.length; i++) {
            String first = str[i];
            String[] nexts = removeString(str, i);
            TreeSet<String> next = process(nexts);
            for(String cur : next) {
                res.add(first + cur);
            }
        }
        return res;
    }

    public static String[] removeString(String[] strs, int i) {
        String[] res = new String[strs.length - 1];
        int idx = 0;
        for (int j = 0; j < strs.length; j++) {
            if (j != i) {
                res[idx++] = strs[j];
            }
        }
        return res;
    }



    // for test
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 5);
            ans[i] = (Math.random() <= 0.5) ? (char) (65 + value) : (char) (97 + value);
        }
        return String.valueOf(ans);
    }

    // for test
    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }

    // for test
    public static String[] copyStringArray(String[] arr) {
        String[] ans = new String[arr.length];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = String.valueOf(arr[i]);
        }
        return ans;
    }



    public static void main(String[] args) {
        int arrLen = 6;
        int strLen = 5;
        int testTimes = 10000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String[] arr1 = generateRandomStringArray(arrLen, strLen);
            String[] arr2 = copyStringArray(arr1);
            if (!lowestString1(arr1).equals(lowestString2(arr2))) {
                for (String str : arr1) {
                    System.out.print(str + ",");
                }
                System.out.println();
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
