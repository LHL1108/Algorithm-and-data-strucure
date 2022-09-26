package class03;

public class Code04_RingArray {
    // 使用数组实现队列
    public static class MyQueue {
        private int[] arr;
        private int pushi;
        private int popi;
        private int size;
        private final int limit;

        public MyQueue(int limit) {
            arr = new int[limit];
            pushi = 0;
            popi = 0;
            size = 0;
            this.limit = limit;
        }

        private int getNextIdx(int idx) {
            return idx < limit - 1 ? idx + 1 : 0;
        }

        public void push(int data) {
            if (size < limit) {
                arr[pushi] = data;
                size++;
                pushi = getNextIdx(pushi);
            } else {
                throw new RuntimeException("队列已满，无法添加");
            }
        }

        public int pop() {
            if (size == 0) {
                throw new RuntimeException("队列已空，无法取出");
            } else {
                int ans = arr[popi];
                size--;
                popi = getNextIdx(popi);
                return ans;
            }
        }
    }

    public static void main(String[] args) {
        MyQueue q = new MyQueue(3);
        q.push(1);
        q.push(2);
        q.push(3);

        System.out.println(q.pop());
        System.out.println(q.pop());
        System.out.println(q.pop());
    }
}
