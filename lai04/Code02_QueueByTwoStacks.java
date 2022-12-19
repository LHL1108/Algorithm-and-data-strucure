package lai04;
/*
[Question]
    use two stack to compliment a queue's offer, poll, peek, size, isEmpty function, return value or null
[Idea]
    one in stack and one out stack, update size after operation
[Construction]
    initialization
        in stack, out stack, size
    offer -> put into in stack, size + 1
    peek -> read first out element, move from in stack if it is empty
    poll -> get from out stack, size - 1, move from in stack if it is empty
    isEmpty -> size == 0
[Notice]
    update size after operation
    use size to judge empty when poll or peek
*/

import java.util.LinkedList;

public class Code02_QueueByTwoStacks {

    public static class QueueByTwoStacks {
        int size;
        LinkedList<Integer> in;
        LinkedList<Integer> out;

        public QueueByTwoStacks() {
            in = new LinkedList<Integer>();
            out = new LinkedList<Integer>();
        }

        public void offer(int e) {
            in.offerFirst(e);
            size++;
        }

        public Integer poll() {
            if (size == 0) {
                return null;
            }
            if (out.isEmpty()) {
                while (!in.isEmpty()) {
                    out.offerFirst(in.pollFirst());
                }
            }
            size--;
            return out.pollFirst();
        }

        public Integer peek() {
            if (size == 0) {
                return null;
            }
            if (out.isEmpty()) {
                while (!in.isEmpty()) {
                    out.offerFirst(in.pollFirst());
                }
            }
            return out.peekFirst();
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }
    }

    public static void main(String[] args) {
        QueueByTwoStacks myQueue = new QueueByTwoStacks();
        myQueue.offer(1);
        myQueue.offer(2);
        myQueue.offer(3);
        myQueue.offer(2);

        //41232
        System.out.println(myQueue.size());
        while (!myQueue.isEmpty()) {
            System.out.println(myQueue.poll());
        }
    }
}
