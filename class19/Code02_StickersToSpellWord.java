package class19;
/*
要求： 有一个目标字符串str,例如"babac“,和一个字符串类型数组arr，例如{"ba", "c", "abcd"}, arr中的每一张贴纸都有无数张，且可以任意剪开拼接，求能拼成目标串所需的最少张数，
      以示例为例，返回2， 即"ba"和"abcd"
链接：https://leetcode.com/problems/stickers-to-spell-word
思路： 把原问题转换为 每种都试，最小值用1+后续最小张数来算，答案必在其中

优化：1.剪枝叶，在首轮尝试中，不试不含有首字母的stick
     2.将stickers和target全转成词频表，直接+-，速度更快

代码1：
    主函数
        调用递归函数
        调整返回值
            系统最大值 -> -1
        返回
    递归函数
        异常判断
            目标长度为0
                返回0
        初始化
            最小值
                为系统最大值
            尝试每个贴纸
                去重
                是否成功消除部分
                    是
                        更新最小值
                            当前最小值和1+后续最小值的小值
                    否
                        不做处理
            计算最小值答案
                是否为系统最大值
                    是
                        返回系统最大值
                    否
                        返回当前最小值
            返回
    消除函数
        数据类型转换
            String -> char[]
        初始化
            StringBuilder
            数据词频表
        计算词频表
            大的++
            小的--
        根据词频表更新StringBuilder
        转换字符串
        返回
*/



public class Code02_StickersToSpellWord {
    public int minStickers(String[] stickers, String target) {
        int res = process(stickers, target);
        res = (res == Integer.MAX_VALUE) ? -1 : res;
        return res;
    }

    public static int process(String[] stickers, String target) {
        if (target.length() == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        for (String first : stickers) {
            String rest = minus(target, first);
            if (rest.length() < target.length()) {
                ans = Math.min(ans, process(stickers, rest));
            }
        }
        if (ans == Integer.MAX_VALUE) {
            ans = Integer.MAX_VALUE;
        } else {
            ans += 1;
        }
        return ans;
    }

    public static String minus(String target, String first) {
        char[] t = target.toCharArray();
        char[] f = first.toCharArray();
        StringBuilder builder = new StringBuilder();
        int[] table = new int[26];
        for (char c : t) {
            table[c - 'a']++;
        }
        for (char c : f) {
            table[c - 'a']--;
        }

        for (int i = 0; i < 26; i++) {
            if (table[i] > 0) {
                for (int j = 0; j < table[i]; j++) {
                    builder.append((char)('a' + i));
                }
            }
        }
        return builder.toString();
    }


    public int minStickers2(String[] stickers, String target) {

        int res = Integer.MAX_VALUE;
        int N = stickers.length;
        int[][] scount = new int[N][26];
        for (int i = 0; i < N; i++) {
            char[] s = stickers[i].toCharArray();
            for (int j = 0; j < s.length; j++) {
                scount[i][s[j]-'a']++;
            }
        }
        res = process2(scount, target);
        res = (res == Integer.MAX_VALUE) ? -1 : res;
        return res;
    }

    public static int process2(int[][] stickers, String target) {
        if (target.length() == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        char[] t = target.toCharArray();
        int[] tcount = new int[26];
        for (int i = 0; i < t.length; i++) {
            tcount[t[i]-'a']++;
        }

        for (int i = 0; i < stickers.length; i++) {
            if (stickers[i][t[0]-'a'] > 0) {
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    tcount[j] -= stickers[i][j];
                    if (tcount[j] > 0) {
                        builder.append((char)('a' + j));
                    }
                }
                String rest = builder.toString();
                ans = Math.min(ans, process2(stickers, rest));
            }
        }
        ans = (ans == Integer.MAX_VALUE) ? Integer.MAX_VALUE : ans + 1;
        return ans;
    }




}
