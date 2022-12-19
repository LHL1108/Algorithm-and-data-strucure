package class17;
/*
要求： 给定一个字符串，返回该字符串所有可能的排列，注意，必须是保持其先后顺序，每个字符可以要或者不要，要求对结果去重
思路： 使用递归算法，函数含义是，对于一个str, 给定当前判断位置，给定前半段路径，给定结果HashSet，将所有可能的结果添加到HashSet中，basecase是位置越界后返回所有前段
代码：
    主函数
        将输入字符串转换为字符数组
        定义当前位置0
        定义前半段路“”
        定义结果表
        运行递归函数
        将HashSet中的元素全部放入字符串列表中
    递归函数
        basecase
            只要位置等于长度
            返回path
        非basecase
            不要当前只要
                index + 1
            要当前位置
                index+1, path修改
*/


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Code03_PrintAllSubsquences {
    public static List<String> sub(String input) {
        char[] strs = input.toCharArray();
        int index = 0;
        String path = "";
        HashSet<String> hashSet = new HashSet<>();
        List<String> res = new ArrayList<>();

        process(strs, index, path, hashSet);

        for (String s: hashSet) {
            res.add(s);
        }
        return res;
    }

    public static void process(char[] strs, int index, String path, HashSet<String> hashSet) {
        if (index == strs.length) {
            hashSet.add(path);
            return;
        } else{
            process(strs, index + 1, path + String.valueOf(strs[index]), hashSet);
            process(strs, index + 1, path, hashSet);
        }
    }

    public static void main(String[] args) {
        List<String> res = sub("abcc");
        for (String s: res) {
            System.out.println(s);
        }
    }

}

