package lai04;
/*
[Question]
    implement a stack with a min() function, and pop, push, top, return min value in the stack, if empty, return -1
[Idea]
    use another min stack, record the current min value
[Notice]
    Integer is not int and they can not compare, need to use (int) to transform
*/

import java.util.LinkedList;

public class Code03_StackWithMin {


    public static class MinStack {
        int size;
        LinkedList s;
        LinkedList min;

        public MinStack() {
            s = new LinkedList();
            min = new LinkedList();
        }

        public void push(int e) {
            s.offerFirst(e);
            if (size == 0 || e < (int)min.peekFirst()) {
                min.offerFirst(e);
            } else {
                min.offerFirst(min.peekFirst());
            }
            size++;
        }

        public int pop() {
            if (size == 0) {
                return -1;
            }
            int res = (int)s.pollFirst();
            min.pollFirst();
            size--;
            return res;
        }

        public int top() {
            if (size == 0) {
                return -1;
            }
            return (int)s.peekFirst();
        }

        public int min() {
            if (size == 0) {
                return -1;
            }
            return (int)min.peekFirst();
        }

    }

    public static void main(String[] args) {
        MinStack test = new MinStack();
        test.push(5);
        test.push(3);
        test.push(4);
        test.push(1);
        test.pop();
        System.out.println(test.min()); //3
        System.out.println(test.top()); //4
        test.pop();
        test.pop();
        test.pop();
        System.out.println(test.min()); //-1

    }
}
