package class03;

import java.util.LinkedList;
import java.util.Queue;

public class Code07_TwoQueueImplementStack {
    public static class MyStack<T> {
        public Queue<T> q;
        public Queue<T> help;

        public MyStack() {
            q = new LinkedList<T>();
            help = new LinkedList<T>();
        }

        public void push(T data) {
            q.offer(data);
        }

        public T pop() {
            if (q.size() == 0) {
                throw new RuntimeException("栈已空");
            } else {
                while (q.size() > 1) {
                    help.offer(q.poll());
                }
                T ans = q.poll();
                Queue tmp = q;
                q = help;
                help = tmp;
                return ans;
            }
        }

        public T peek() {
            if (q.size() == 0) {
                throw new RuntimeException("栈已空");
            } else {
                while (q.size() > 1) {
                    help.offer(q.poll());
                }
                T ans = q.poll();
                help.offer(ans);
                Queue tmp = q;
                q = help;
                help = tmp;
                return ans;
            }
        }
    }

    public static void main(String[] args) {
        MyStack s = new MyStack<>();
        s.push(1);
        s.push(2);
        System.out.println(s.peek());
        s.push(3);
        s.push(4);
        System.out.println(s.pop());
        System.out.println(s.pop());
        System.out.println(s.pop());
        System.out.println(s.pop());
//      期望结果 24321


    }
}
