package class03;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

public class HashMapAndSortedMap {

    public static void main(String[] args) {
/*        // 哈希表
        HashMap<Integer, String> hashMap = new HashMap<Integer, String>();
        hashMap.put(1, "我是1");
        hashMap.put(2, "我是2");
        hashMap.put(3, "我是3");
        hashMap.put(1, "我是新的1");
        System.out.println(hashMap.get(1));
        System.out.println(hashMap.get(2));
        System.out.println(hashMap.get(3));
        System.out.println("是否包含索引3： " + hashMap.containsKey(3));
        */
        // 有序表
        TreeMap<Integer, String> sortedMap = new TreeMap<Integer, String>();
        sortedMap.put(1, "我是1");
        sortedMap.put(2, "我是2");
        sortedMap.put(3, "我是3");
        sortedMap.put(1, "我是新的1");
        System.out.println(sortedMap.get(1));
        System.out.println(sortedMap.get(2));
        System.out.println(sortedMap.get(3));
        System.out.println("是否包含索引3： " + sortedMap.containsKey(3));

        System.out.println("【有序表独有特性】");
        System.out.println("返回首个索引： " + sortedMap.firstKey());
        System.out.println("返回末尾索引： " + sortedMap.lastKey());
        System.out.println("返回距离4上方最近的索引： " + sortedMap.floorKey(4));
        System.out.println("返回距离2下方最近的索引： " + sortedMap.ceilingKey(2));
    }

}
