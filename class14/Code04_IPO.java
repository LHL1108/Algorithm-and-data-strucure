package class14;

import java.util.Comparator;
import java.util.PriorityQueue;
/*
要求：每个项目有一个花费c和纯利p，初始资金K，和最终资金W，只能一个项目一个项目进行，每进行一个项目就要有所花费并赚取纯利，求能取得的最大W
思路：在能进行的项目之中选择纯利最大的
代码思路：
	定义比较方法
		小成本堆
		大利润堆
	初始化
		小成本堆
		大利润堆
	将所有项目放入小成本堆中
	在所有项目选定之前
		在有项目的前提下，将符合启动要求的项目放入大利润堆中
		大利润堆中无项目则提前返回
		更新总利润
	返回总利润
易错：
    在选取小顶堆元素做判断的时候，用的是peek而非poll
*/

public class Code04_IPO {

    public static class MinCostComparator implements Comparator<Program>{
        @Override
        public int compare(Program o1, Program o2){
            return o1.c - o2.c;
        }
    }

    public static class MaxProfitComparator implements Comparator<Program>{
        @Override
        public int compare(Program o1, Program o2){
            return o2.p - o1.p;
        }
    }

    public static int findMaximizedCapital(int K, int W, int[] Profits, int[] Capital){
        PriorityQueue<Program> minCostPrograms = new PriorityQueue<Program>();
        PriorityQueue<Program> maxProfitPrograms = new PriorityQueue<Program>();
        for (int i = 0; i < Profits.length; i++) {
            minCostPrograms.add(new Program(Profits[i], Capital[i]));
        }

        for (int i = 0; i < K; i++) {
            while(!minCostPrograms.isEmpty() && minCostPrograms.peek().c <= W){
                maxProfitPrograms.add(minCostPrograms.poll());
            }
            if(maxProfitPrograms.isEmpty()) {
                return W;
            }
            W += maxProfitPrograms.poll().p;
        }
        return W;
    }


    public static class Program {
        public int p;
        public int c;

        public Program(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }

    public static void main(String[] args) {
        System.out.println("finish");
    }


}
