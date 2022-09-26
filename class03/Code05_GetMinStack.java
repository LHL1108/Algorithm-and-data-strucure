package class03;
import java.util.Stack;

public class Code05_GetMinStack {
    public static class MyStack {
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        public MyStack() {
            this.stackData = new Stack<Integer>();
            this.stackMin = new Stack<Integer>();
        }

        public void push(int data) {
            if (this.stackMin.isEmpty()){
                this.stackMin.push(data);
            } else {
                if (data < this.stackMin.peek()){
                    this.stackMin.push(data);
                }else{
                    this.stackMin.push(this.stackMin.peek());
                }
            }
            this.stackData.push(data);
        }

        public int pop() {
            if (this.stackData.isEmpty()) {
                throw new RuntimeException("栈已空，无法弹出");
            } else {
                this.stackMin.pop();
            }
            return this.stackData.pop();
        }

        public int getMin() {
            if (this.stackMin.isEmpty()) {
                throw new RuntimeException("栈中无数据");
            } else {
                return this.stackMin.peek();
            }
        }
    }


    public static void main(String[] args) {
        MyStack stack1 = new MyStack();
        stack1.push(1);
        stack1.pop();
        stack1.push(2);
        int m = stack1.getMin();
        stack1.push(3);
        stack1.push(1);
        System.out.println(stack1.getMin());
    }
}
