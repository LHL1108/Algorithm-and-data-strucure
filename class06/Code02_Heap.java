package class06;

public class Code02_Heap {
    /*
    要求： 实现一个大顶堆，包含isEmpty, isFull, push, pop几项基本操作
    思路：
        1.堆的底层数据结构是一个数组heap，只是具有limit, size, 和特殊的元素排序
        2. isEmpty, isFull，容易判断，push要做heapInsert操作，pop要交换收尾，改变长度，heapify
    易错：
        1.heapify过程 算出left < size即代表有左子，程序可向下运行，此时是下标，  heapInsert过程儿子的值大于爹的值 程序就可继续运行
        2.push 和 pop后对size的++和--操作
    */

    public static class MyMaxHeap {
        private int limit;
        private int size;
        private int[] heap;
        public MyMaxHeap(int limit) {
            this.limit = limit;
            size = 0;
            heap = new int[limit];
        }

        public boolean isEmpty() {
            if (size == 0) {
                return true;
            } else {
                return false;
            }
        }

        public boolean isFull() {
            if (size == limit) {
                return true;
            } else {
                return false;
            }
        }

        public static void swap(int[] arr, int i, int j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }

        public static void heapInsert(int[] heap, int idx) {
            // 看他爹，比爹大就换，否则就不换跳出循环 1 34
            int father =  ((idx - 1) / 2);
            while (heap[idx] > heap[father]) {
                swap(heap, idx, father);
                idx = father;
                father =  ((father - 1) / 2);
            }
        }

        public static void heapify(int[] heap, int idx, int size) {
            // 看他儿，比儿小就换，否则就不换，跳出循环
            int left = (2 * idx) + 1;
            while (left < size){
                int largerIdx = ((left + 1) <= size - 1) && heap[left + 1] > heap[left] ? left + 1 : left;
                largerIdx = heap[idx] > heap[largerIdx] ? idx : largerIdx;
                if (idx == largerIdx) {
                    break;
                }
                swap(heap, idx, largerIdx);
                idx = largerIdx;
                left = (2 * idx) + 1;
            }

        }


        public void push(int value) {
            if (size == limit) {
                throw new RuntimeException("the heap is full.");
            }
            heap[size] = value;
            heapInsert(heap, size);
            size++;
        }

        public int pop() {
            if (size == 0) {
                throw new RuntimeException("Heap is empty.");
            }
            int res = heap[0];
            swap(heap, 0, size - 1);
            size--;
            heapify(heap, 0, size);
            return res;
        }

/////////////////////////////////////////////////////////////////////////////////////

        public static void main(String[] args) {
            MyMaxHeap Myheap = new MyMaxHeap(5);
            Myheap.push(2);
            Myheap.push(4);
            Myheap.push(1);
            Myheap.push(6);
            Myheap.push(5);
            while(!Myheap.isEmpty()) {
                System.out.println(Myheap.pop());
            }
            // should return 6 5 4 3 2 1
        }
    }
}
