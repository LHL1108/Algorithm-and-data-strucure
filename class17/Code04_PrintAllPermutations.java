package class17;

import java.util.ArrayList;
import java.util.List;

/*
要求：给定一个字符串，求其全排列
思路：使用迭代函数，由于全排列必定每个都要，所以函数内部需要涵盖对剩余字符的遍历，迭代函数为，给定一个字符串，给定当前结果和剩余字符以及结果数组，将所有可能的结果返回至结果数组中。
代码思路：
    主函数
        确定初始条件
            字符数组
            当前路径
            剩余字符
            结果数组
        运行迭代函数
        返回结果
    迭代函数
        basecase
            当剩余结果为空的时候
            将路径返回至结果中
        遍历剩余结果
            将当前的剩余字符去掉该字符
            路径加入
            继续跑迭代函数

思路2： 使用交换方法，且去重。每次交换2个元素，进入迭代函数，再交换回来


*/
public class Code04_PrintAllPermutations {

    public static List<String> permutations(String str) {
        char[] strs = str.toCharArray();
        String path = "";
        List<Character> rest = new ArrayList<>();
        for(Character s : strs) {
            rest.add(s);
        }
        List<String> ans = new ArrayList<>();
        process(strs, path, rest, ans);
        return ans;
    }

    public static void process(char[] strs, String path, List<Character>rest, List<String>ans) {
        if (rest.isEmpty()) {
            ans.add(path);
            return;
        }
        int N = rest.size();
        for (int i = 0; i < N; i++) {
            Character c = rest.get(i);
            rest.remove(i);
            process(strs, path + c, rest, ans);
            rest.add(i, c);
        }
    }




    public static List<String> permutations2(String str) {
        char[] strs = str.toCharArray();
        List<String> res = new ArrayList<>();
        if (str == null || str.length() == 0){
            res.add("");
        }

        process2(strs, 0, res);
        return res;
    }

    public static void swap(char[] strs, int i, int j) {
        char tmp = strs[i];
        strs[i] = strs[j];
        strs[j] = tmp;
    }

    public static void process2(char[] strs, int index, List<String> res) {
        if (index == strs.length) {
            res.add(String.valueOf(strs));
        }
        boolean[] visited = new boolean[256];
        for (int i = index; i < strs.length; i++) {
            if(visited[strs[i]]==false) {
                visited[strs[i]] = true;
                swap(strs, index, i);
                process2(strs, index + 1, res);
                swap(strs, index, i);
            }

        }
    }







    public static void main(String[] args) {
        //String str = "abc";
        String str = "ccc";
        List<String> ans = permutations(str);
        List<String> ans2 = permutations2(str);

        for (String item : ans) {
            System.out.println(item);
        }
        System.out.println("-----------------------------------");
        for (String item2 : ans2) {
            System.out.println(item2);
        }
    }
}
