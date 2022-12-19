package lai06;
/*
[Question]
    given an unsorted array, output k smallest numbers in ascending order
[Idea]
    use a k length max heap
    put k elements directly
    put rest elements into heap if it is smaller than the top of heap
    put elements from a heap to a array
    output array from right to left
[Notice]
    how to write and use a comparator, and @Override with two implements
    max heap so o2 - o1
    even though we give a size of heap, but we still need to poll before offer, other wise the size will expand
    we can just poll the item and put it from the arr's right to the left
[Complexity]
    Time:  O(NlogK) put element directly: KlogK  put rest elements: (N-K)logK
    Space: O(K), we only use a K size heap
*/

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Code01_KSmallestInUnsortedArray {

    public static class MyComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }

    public static int[] kSmallest(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k == 0) {
            return new int[0];
        }
        int[] res = new int[k];
        //PriorityQueue<Integer> heap = new PriorityQueue<Integer>(k, new MyComparator());
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2){
                return o2 - o1;
            }
        });

        for (int i = 0; i < k; i++) {
            heap.offer(arr[i]);
        }
        for (int i = k; i < arr.length; i++) {
            if (arr[i] < heap.peek()) {
                heap.poll();
                heap.offer(arr[i]);
            }
        }

        for (int i = k - 1; i >= 0; i--) {
            res[i] = heap.poll();
        }
        return res;
    }






    public static void main(String[] args) {

        int[] arr = {6, 5, 2, 4, 1, 3, 7};
        int k = 3;

        int[] res = kSmallest(arr, k);

        // 123
        System.out.println(Arrays.toString(res));
    }
}
