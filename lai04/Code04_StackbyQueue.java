package lai04;
import java.util.Deque;
import java.util.LinkedList;

/*
[Question]
    use queues to implement a stack
[Idea]
    always keep all elements in one queue, which is main queue, if need to push, just push, if need to pop,
    only left one element in main, pop it and then change main and help queue,
[Process]
    fields
    constructor
    push
        push
        change size
    pop
        move all elements but last
        pop
        change size
    top
*/
public class Code04_StackbyQueue {


    public static class MyStack {

        int size;
        Deque<Integer> q1;
        Deque<Integer> q2;

        public MyStack(){
            q1 = new LinkedList<Integer>();
            q2 = new LinkedList<Integer>();
        }

        public void push(int e) {
            q1.offerFirst(e);
            size++;
        }

        public Integer pop() {
            if (size == 0) {
                return null;
            } else {
                for (int i = size; i > 1; i--) {
                    q2.offerFirst(q1.pollLast());
                }
                int res = q1.pollLast();
                Deque<Integer> tmp = q1;
                q1 = q2;
                q2 = q1;
                size--;
                return	res;
            }
        }

        public Integer top() {
            if (size == 0) {
                return null;
            } else {
                for (int i = size; i > 1; i--) {
                    q2.offerFirst(q1.pollLast());
                }
                int res = q1.pollLast();
                q2.offerFirst(res);
                Deque<Integer> tmp = q1;
                q1 = q2;
                q2 = q1;
                return	res;
            }
        }

        public boolean isEmpty() {
            return size == 0;
        }
    }


    public static void main(String[] args) {
        MyStack s = new MyStack();
        s.push(1);
        s.push(2);
        s.push(3);

        // 3 3 2 false
        System.out.println(s.top());
        System.out.println(s.pop());
        System.out.println(s.pop());
        System.out.println(s.isEmpty());
    }











}
