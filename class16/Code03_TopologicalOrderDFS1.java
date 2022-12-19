package class16;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

// OJ链接：https://www.lintcode.com/problem/topological-sorting
public class Code03_TopologicalOrderDFS1 {

    // 不要提交这个类
    public static class DirectedGraphNode {
        public int label;
        public ArrayList<DirectedGraphNode> neighbors;

        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph){
        HashMap<DirectedGraphNode, Record> order = new HashMap<>();
        ArrayList<Record> sortArray = new ArrayList<>();
        ArrayList<DirectedGraphNode> res = new ArrayList<>();

        for(DirectedGraphNode cur : graph) {
            f(cur, order);
        }

        for(Record r : order.values()){
            sortArray.add(r);
        }
        sortArray.sort(new MyComparator());

        for(Record r : sortArray){
            res.add(r.node);
        }

        return res;
    }

    public static class MyComparator implements Comparator<Record>{
        @Override
        public int compare(Record r1, Record r2){
            return r2.deep - r1.deep;
        }
    }


    public static class Record{
        public DirectedGraphNode node;
        public int deep;
        public Record(DirectedGraphNode n, int d){
            node = n;
            deep = d;
        }
    }

    public static Record f(DirectedGraphNode cur, HashMap<DirectedGraphNode, Record> order){
        if(order.containsKey(cur)){
            return order.get(cur);
        }

        int deep = 0;
        for(DirectedGraphNode next : cur.neighbors){
            deep = Math.max(deep, f(next, order).deep);
        }
        Record ans = new Record(cur, deep + 1);
        order.put(cur, ans);
        return ans;
    }






}
