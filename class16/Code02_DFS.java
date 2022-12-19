package class16;

import java.util.HashSet;
import java.util.Stack;
/*
要求： 解释并编写图结构的DFS深度优先遍历
思路： DFS简单说就是一条路没走完就走到死，如果走死了就逐渐往上折返看看还有没有其他能走的，也就是优先探底
       从实现上使用的结构为栈和哈希表
       运行条件仍然是只要栈不为空，就看栈顶元素的后继节点，只要不在set里，就将后继重新压入，为看它，下次弹出做准备，并且不看其他的后继，所以break
代码思路：
        创建 如果为空
        返回
    初始化
        赋值
    打印
    只要栈不为空
        弹出栈顶元素
        对于栈顶元素的每一个后继
            只要该元素不在哈希表中
                把当前元素压入栈
                把当前后继压入栈
                打印
                哈希表添加该后继
                跳出循环
*/

public class Code02_DFS {
    public static void DFS(Node start) {
        if(start != null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        stack.add(start);
        set.add(start);
        System.out.println(start.value);

        while(!stack.isEmpty()){
            Node cur = stack.pop();
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    stack.push(cur);
                    stack.push(next);
                    System.out.println(next);
                    set.add(next);
                    break;
                }
            }
        }
    }
}
