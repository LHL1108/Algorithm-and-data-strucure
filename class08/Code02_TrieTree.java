package class08;

import java.util.HashMap;
import java.util.TreeMap;

public class Code02_TrieTree {
    /*
    要求： 实现一个前缀树Trie1，包含insert(添加某个字符串路径), delete(删除某个字符串路径),search(查询某字符串之前加入过几次), prefixNumber(该前缀出现几次)功能
    思路：
        1.前缀树为节点的递归结构，包含pass,end,Node三个属性
        2.insert的关键点在于没有就构建，有就pass++,最后end++
        3.delete的关键点在于首先先search一遍，存在再删，同时，删的过程中，如果某路pass==1,它又必存在，那么当前节点一定是该路径，直接将其置null，后面可以不做处理，内存自动丢失
        4.search的关键点在于中间如果找不到，提前返回0，否则返回结尾的end
        5.prefixNumber的关键点在于返回的是pass而不是end，找不到提前返回
    易错点：
        Node的next属性是一个长度为26的，只有一个值非空的Node数组
        字符的asc2码 - 'a'及为下标
        不要对根节点直接处理，而是要先复制一个node
        删除过程，只要中间删了，后面的字符因为无法找到，等同于删除
        删之前需要先search一下，能找到再开始删，可以确定删除操作不会做错
    */

    // 一步一步来
    public static class Node{
        private int pass;
        private int end;
        private Node[] nexts;

        public Node() {
            pass = 0;
            end = 0;
            nexts = new Node[26];
        }
    }

    public static class Trie1 {
        private Node root;

        public Trie1() {
            root = new Node();
        }

        public void insert(String words) {
            if (words == null) {
                return;
            }
            char[] wds = words.toCharArray();
            int path = 0;
            Node node = root;
            node.pass++;
            for (int i = 0; i < wds.length; i++) {
                path = wds[i] - 'a';
                if (node.nexts[path] == null) {
                    node.nexts[path] = new Node();
                }
                node = node.nexts[path];
                node.pass++;
            }
            node.end++;
        }

        public int search(String words) {
            if (words == null) {
                return 0;
            }

            int path = 0;
            Node node = root;
            char[] wds = words.toCharArray();
            for (int i = 0; i < wds.length; i++) {
                path = wds[i] - 'a';
                if (node.nexts[path] == null) {
                    return 0;
                }
                node = node.nexts[path];
            }
            return node.end;
        }

        public void delete(String words) {
            if (words == null || search(words) == 0) {
                return;
            }

            char[] wds = words.toCharArray();
            int path = 0;
            Node node = root;
            node.pass--;

            for (int i = 0; i < wds.length; i++) {
                path = wds[i] - 'a';
                node.nexts[path].pass--;
                if (node.nexts[path].pass == 0) {
                    node.nexts[path] = null;
                    return;
                }
                node = node.nexts[path];
            }
            node.end--;
        }

        public int prefixNumber(String words) {
            if (words == null) {
                return 0;
            }
            char[] wds = words.toCharArray();
            int path = 0;
            Node node = root;

            for (int i = 0; i < wds.length; i++) {
                path = wds[i] - 'a';
                if (node.nexts[path] == null) {
                    return 0;
                }
                node = node.nexts[path];
            }
            return node.pass;
        }
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // for test


    public static class Node2 {
        public int pass;
        public int end;
        public HashMap<Integer, Node2> nexts;

        public Node2() {
            pass = 0;
            end = 0;
            nexts = new HashMap<>();
        }
    }

    public static class Trie2 {
        private Node2 root;

        public Trie2() {
            root = new Node2();
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }
            char[] chs = word.toCharArray();
            Node2 node = root;
            node.pass++;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = (int) chs[i];
                if (!node.nexts.containsKey(index)) {
                    node.nexts.put(index, new Node2());
                }
                node = node.nexts.get(index);
                node.pass++;
            }
            node.end++;
        }

        public void delete(String word) {
            if (search(word) != 0) {
                char[] chs = word.toCharArray();
                Node2 node = root;
                node.pass--;
                int index = 0;
                for (int i = 0; i < chs.length; i++) {
                    index = (int) chs[i];
                    if (--node.nexts.get(index).pass == 0) {
                        node.nexts.remove(index);
                        return;
                    }
                    node = node.nexts.get(index);
                }
                node.end--;
            }
        }

        // word这个单词之前加入过几次
        public int search(String word) {
            if (word == null) {
                return 0;
            }
            char[] chs = word.toCharArray();
            Node2 node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = (int) chs[i];
                if (!node.nexts.containsKey(index)) {
                    return 0;
                }
                node = node.nexts.get(index);
            }
            return node.end;
        }

        // 所有加入的字符串中，有几个是以pre这个字符串作为前缀的
        public int prefixNumber(String pre) {
            if (pre == null) {
                return 0;
            }
            char[] chs = pre.toCharArray();
            Node2 node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = (int) chs[i];
                if (!node.nexts.containsKey(index)) {
                    return 0;
                }
                node = node.nexts.get(index);
            }
            return node.pass;
        }
    }

    public static class Right {

        private HashMap<String, Integer> box;

        public Right() {
            box = new HashMap<>();
        }

        public void insert(String word) {
            if (!box.containsKey(word)) {
                box.put(word, 1);
            } else {
                box.put(word, box.get(word) + 1);
            }
        }

        public void delete(String word) {
            if (box.containsKey(word)) {
                if (box.get(word) == 1) {
                    box.remove(word);
                } else {
                    box.put(word, box.get(word) - 1);
                }
            }
        }

        public int search(String word) {
            if (!box.containsKey(word)) {
                return 0;
            } else {
                return box.get(word);
            }
        }

        public int prefixNumber(String pre) {
            int count = 0;
            for (String cur : box.keySet()) {
                if (cur.startsWith(pre)) {
                    count += box.get(cur);
                }
            }
            return count;
        }
    }

    // for test
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 6);
            ans[i] = (char) (97 + value);
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

    public static void main(String[] args) {
        int arrLen = 100;
        int strLen = 20;
        int testTimes = 1000;
        for (int i = 0; i < testTimes; i++) {
            String[] arr = generateRandomStringArray(arrLen, strLen);
            Trie1 trie1 = new Trie1();
            Trie2 trie2 = new Trie2();
            Right right = new Right();
            for (int j = 0; j < arr.length; j++) {
                double decide = Math.random();
                if (decide < 0.25) {
                    trie1.insert(arr[j]);
                    trie2.insert(arr[j]);
                    right.insert(arr[j]);
                } else if (decide < 0.5) {
                    trie1.delete(arr[j]);
                    trie2.delete(arr[j]);
                    right.delete(arr[j]);
                } else if (decide < 0.75) {
                    int ans1 = trie1.search(arr[j]);
                    int ans2 = trie2.search(arr[j]);
                    int ans3 = right.search(arr[j]);
                    if (ans1 != ans2 || ans2 != ans3) {
                        System.out.println("Oops!");
                    }
                } else {
                    int ans1 = trie1.prefixNumber(arr[j]);
                    int ans2 = trie2.prefixNumber(arr[j]);
                    int ans3 = right.prefixNumber(arr[j]);
                    if (ans1 != ans2 || ans2 != ans3) {
                        System.out.println("Oops!");
                    }
                }
            }
        }
        System.out.println("测试通过!");

    }

























}
