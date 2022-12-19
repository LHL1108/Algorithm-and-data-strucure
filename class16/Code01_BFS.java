package class16;
/*
要求： 对图结构实现BFS宽度优先遍历

思路： 同树结构的按层遍历，所需结构队列、哈希表，哈希表作用，记录哪些节点已经被加入到队列中，避免重复
      队列作用，储存等待遍历的元素

代码思路：
    如果输入为空
        直接返回
    初始化结构
        创建
        赋值
    只要队列不为空
        对于队首元素
            弹出
            打印
        对于弹出元素的后继
            判断是否已遍历
                否
                    加入队列
                    加入哈希表
*/

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Code01_BFS {
    public static void BFS(Node start){
        if (start == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();
        queue.add(start);
        set.add(start);

        while(!queue.isEmpty()){
            Node cur = queue.poll();
            System.out.println(cur.value);
            for (Node next: cur.nexts) {
                if (!set.contains(next)) {
                    queue.add(next);
                    set.add(next);
                }
            }
        }
    }





}
