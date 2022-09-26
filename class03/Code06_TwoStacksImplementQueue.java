package class03;
import java.util.Stack;

public class Code06_TwoStacksImplementQueue {
    public static class MyQueue {
        Stack<Integer> stackPush = new Stack<Integer>();
        Stack<Integer> stackPop = new Stack<Integer>();

        public void MyQueue() {
            stackPush = new Stack<Integer>();
            stackPop = new Stack<Integer>();
        }

        private void pollElement() {
            if (stackPop.isEmpty()) {
                while(!stackPush.isEmpty()) {
                    stackPop.push(stackPush.pop());
                }
            }
        }

        public void add(int data) {
            pollElement();
            stackPush.push(data);
        }

        public int poll() {
            pollElement();
            if (stackPop.isEmpty()) {
                throw new RuntimeException("队列已空");
            } else {
                return stackPop.pop();
            }
        }

        public int peek() {
            pollElement();
            if (stackPop.isEmpty()) {
                throw new RuntimeException("队列已空");
            } else {
                return stackPop.peek();
            }
        }
    }

    public static void main(String[] args) {
        MyQueue q = new MyQueue();
        q.add(1);
        q.poll();
        q.add(2);
        System.out.println(q.peek());
        q.add(3);
        System.out.println(q.poll());
        System.out.println(q.poll());
    }

}
