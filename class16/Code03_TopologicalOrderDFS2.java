package class16;

import class13.Code05_LowestLexicography;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

// OJ链接：https://www.lintcode.com/problem/topological-sorting
/*
要求： 返回图结构的拓扑序
思路： 确定每个节点的“后续点次”，将其降序排序，即为拓扑序。需要辅助存储结构record，记录每个节点的点次情况。

代码思路：
    创建辅助结构记录
        节点
        点次
    主函数
        初始化
            创建存储结构order
                节点，记录
            创建Record数组
            创建结果数组

        处理order数组
            对图中的每个节点
                计算其点次并存储至缓存

        处理record数组
            对缓存中的每个值
                加入值Record数组
            对record数组进行降序排序

        处理结果数组
            对record中的每一个记录
                将其节点放入结果中
    计算函数f
        输入：节点，缓存
        如果缓存包含节点
            直接返回该节点的点次
        如果不包含
            点次初始为0
            对于该节点的每一个后继
                节点数加等于计算函数(后继)的节点数
        生成结果记录，当前节点，记录加1
        将其放入缓存
        返回
    排序函数
        对Record进行排序
        谁点次大谁排前面
*/


public class Code03_TopologicalOrderDFS2 {

    // 不要提交这个类
    public static class DirectedGraphNode {
        public int label;
        public ArrayList<DirectedGraphNode> neighbors;

        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    public static class Record{
        public DirectedGraphNode node;
        public long nodes;

        public Record(DirectedGraphNode n1, long n2){
            node = n1;
            nodes = n2;
        }
    }

    public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph){
        HashMap<DirectedGraphNode, Record> order = new HashMap<>();
        ArrayList<Record> sortArray = new ArrayList<>();
        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        for (DirectedGraphNode cur : graph) {
            f(cur, order);
        }

        for(Record r : order.values()){
            sortArray.add(r);
        }
        sortArray.sort(new MyComparator());

        for (Record r : sortArray) {
            ans.add(r.node);
        }
        return ans;
    }

    public static class MyComparator implements Comparator<Record>{
        @Override
        public int compare(Record r1, Record r2){
            if (r1.nodes == r2.nodes){
                return 0;
            }
            return r1.nodes > r2.nodes ? -1 : 1;
        }
    }


    public static Record f(DirectedGraphNode cur, HashMap<DirectedGraphNode, Record> order) {
        if(order.containsKey(cur)){
            return order.get(cur);
        }

        int nodes = 0;
        for (DirectedGraphNode next : cur.neighbors) {
            nodes += f(next, order).nodes;
        }
        Record ans = new Record(cur, nodes + 1);
        order.put(cur, ans);
        return ans;
    }

}
