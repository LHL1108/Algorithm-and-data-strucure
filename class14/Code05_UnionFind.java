package class14;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;
/*
要求： 解释并实现并查集
并查集的本质：并查集是一种多叉树集合，用来实现快速确认两个节点是否属于同一集合，并且支持两个集合的合并功能，在调用次数足够多的情况下，平均时间复杂度可以达到O(1)
整体思路：
	并查集结构
		连接value和node的HashMap nodes
		记录每个节点的父亲节点的HashMap parents
		记录每个代表节点所在集合大小的HashMap sizeMap
	在初始状态下，每一个节点单独属于一个集合，指向自己
	找爹函数，给一个值，返回往上到不能再往上的代表节点，用栈实现扁平化的优化
	确认函数，直接找爹并进行比较
	合并函数，在节点合并的过程中，仅改变小树老大，令其指向大树老大

代码思路
    初始参数
        value
        parents
        sizemap
    构造函数
        初始化
        传参
    找爹函数
        建立路径栈
        循环找爹并在过程中压栈
        弹出栈中元素并连接代表节点
    确认函数
        找爹
        比较
    合并函数
        找爹
        爹不同时
            确定谁大谁小
            小的代表函数连大的代表函数
        更新参数
易错点：
    爹不同时才进行操作
    合并函数是找祖先而不是找父亲
*/



public class Code05_UnionFind {

    public static class Node<V> {
        V value;

        public Node(V v) {
            value = v;
        }
    }

    public static class UnionFind<V> {
        public HashMap<V, Node<V>> nodes;
        public HashMap<Node<V>, Node<V>> parents;
        public HashMap<Node<V>, Integer> sizeMap;

        public UnionFind(List<V> values) {
            nodes = new HashMap<>();
            parents = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V value:values) {
                Node<V> node = new Node<>(value);
                nodes.put(value, node);
                parents.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public Node<V> findFather(Node<V> cur) {
            Stack<Node<V>> stack = new Stack<>();
            while(parents.get(cur) != cur) {
                stack.push(cur);
                cur = parents.get(cur);
            }
            while(!stack.isEmpty()){
                parents.put(stack.pop(), cur);
            }
            return cur;
        }

        public boolean isSameSet(V a, V b) {
            return findFather(nodes.get(a)) == findFather(nodes.get(b));
        }

        public void union(V a, V b) {
            Node<V> aparents = findFather(nodes.get(a));
            Node<V> bparents = findFather(nodes.get(b));
            if(aparents != bparents){
                Node<V> big = sizeMap.get(aparents) >= sizeMap.get(bparents) ? aparents : bparents;
                Node<V> small = big == aparents ? bparents : aparents;
                parents.put(small, big);
                sizeMap.put(big, sizeMap.get(aparents) + sizeMap.get(bparents));
                sizeMap.remove(small);
            }
        }

        public int sets() {
            return sizeMap.size();
        }
    }

    public static void main(String[] args) {
        System.out.println("finish");
    }
}
