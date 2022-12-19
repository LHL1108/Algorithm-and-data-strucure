package class11;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Code02_SerializeAndReconstructTree {
    /*
    要求：按照前序遍历的方式对一颗二叉树进行序列化和反序列化
    思路：
        1.首先明确好输入输出，分别是节点和列表
        2.其次明确序列化顺序为头左右，递归完成，先写出序列化代码
            写出递归函数
    */

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node (int data) {
            this.value = data;
        }
    }

    // 序列化
    public static Queue<String> preSerialize(Node head) {
        Queue<String> ans = new LinkedList<>();
        pres(head, ans);
        return ans;
    }

    public static void pres(Node head, Queue<String> ans) {
        if (head == null) {
            ans.add(null);
        }else{
            ans.add(String.valueOf(head));
            pres(head.left, ans);
            pres(head.right, ans);
        }
    }

    // 反序列化
    public static Node buildByPre(Queue<String> ans) {
        if (ans.poll() == null) {
            return null;
        }
        Node head = preb(ans);
        return head;
    }

    public static Node preb(Queue<String> ans) {
        if (ans == null || ans.size() == 0) {
            return null;
        }

        Node head = new Node(Integer.valueOf(ans.poll()));
        head.left = preb(ans);
        head.right = preb(ans);
        return head;
    }


}

