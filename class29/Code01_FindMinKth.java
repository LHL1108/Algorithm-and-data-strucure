package class29;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;


/*
[Question]
    Find kth smallest number
[IDEA]
    Method1: use heap, because we need to find kth smallest number, that means we need to find k - 1 smaller number, so we need a k Max heap,
             when the number of elements is less than k, we just need to put into the heap, but if the number of elements is larger than k, we need to
             compare the new element with the top element, if it is larger than or equal to top, just drop it , but if it is smaller than top, we need
             to drop top and put the new element on the top of heap, and then adjust it. imagine we need find top7 in 12345678/12345670
    Method2: quicksort, randomly choose a number in array, put smaller number in the left and put larger number in the right, record the middle part
             boundary index, if k is in the boundary range, then we know the value of Kth smallest number is the value of random number. Otherwise, we
             go deeper in the left part or the right part depends on the ralationship between k and random number boundary.
    Method3: bfprt, it is an improved version of method2, the only difference is way of choosing pivot. In bfprf method, we divide data into groups of 5,
             then we the median of each group's median, use it as pivot. In this way, we don't need to comapre the pivot with the elements in larger parts
             of the larger group
[STRUCTURE]
    -Method1
    Comparator
        descending
            o2-o1
    Main
        initialization
            heap
        add element directly
            before kth element
        add element after comparing
            from k+1, compare before adding
                if new one is bigger or equal
                    drop it
                if new one is smaller
                    drop top
                    add new one on the top

    -Method2
    Main
        copy array
        partition method
    partition method
        choose a random number as pivot
            random index between valid l and r
        sort value by it
            next left
            next right
        next step
            in the range
                return the result
            not in the range
                go deeper into left or right part

    -Method3
    Main
        copy array
        partition method
    partition method
        choose median of median number as pivot
            random index between valid l and r
        sort value by it
            next left
            next right
        next step
            in the range
                return the result
            not in the range
                go deeper into left or right part
    median of median
        divide into groups of 5
        calculate median of each group
            API sort
            get median by idx
        calculate median of medians
            get median with bfprt to maintain O(N)
[PROBLEMS]
    implements is a class, be aware of its spell
    comparator is not familiar
    maxHeap.poll, not remove
    i  0 -> k -> arr.length
    not static in compare
    how to generate random
    not familiar with quicksort, forget while loop
    insert sort, swap what?
[DOUNBT]
    Method2, Bound, why could it only be the equalarea boundary, if I let it be samller and
    larger boundary, it will be wrong
 */

public class Code01_FindMinKth {

    //MaxHeapComparator
    //minKth1
    public static class MaxHeapComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }

    public static int minKth1(int[] arr, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(new MaxHeapComparator());

        for (int i = 0; i < k; i++) {
            maxHeap.add(arr[i]);
        }
        for (int i = k; i < arr.length; i++) {
            if (arr[i] < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.add(arr[i]);
            }
        }

        return maxHeap.peek();
    }

    public static int minKth2(int[] array, int k) {
        int[] arr = copyArray(array);
        int res = process(arr, 0, arr.length - 1, k - 1);
        return res;
    }

    public static int[] copyArray(int[] array) {
        int[] arr = new int[array.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = array[i];
        }
        return arr;
    }

    public static int process(int[] arr, int L, int R, int idx) {
        //judge
        if (L == R) {
            return arr[L];
        }
        // pivot
        int pivot = arr[L + (int)(Math.random() * (R - L + 1))];
        //partition
        int[] bounds = partition(arr, L, R, pivot);
        //compare then return or recursion
        if (idx >= bounds[0] && idx <= bounds[1]) {
            return pivot;
        } else if(idx < bounds[0]) {
            return process(arr, 0, bounds[0] - 1, idx);
        } else {
            return process(arr, bounds[1] + 1, R, idx);
        }
    }

    public static int[] partition(int[] arr, int L, int R, int pivot) {
        int less = L - 1;
        int more = R + 1;
        int cur = L;
        while (cur < more) {
            if (arr[cur] < pivot) {
                swap(arr, ++less, cur++);
            } else if (arr[cur] > pivot) {
                swap(arr, --more, cur);
            } else {
                cur++;
            }
        }
        return new int[] {less + 1, more - 1};
    }

    public static void swap(int[] arr, int idx1, int idx2) {
        int tmp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = tmp;
    }

    public static int minKth3(int[] array, int k) {
        int[] arr = copyArray(array);
        return bfprt(arr, 0, arr.length - 1, k - 1); ///
    }

    public static int bfprt(int[] arr, int L, int R, int idx) {
        // judge
        if (L == R) {
            return arr[L];
        }
        // pivot
        int pivot = medianOfMedian(arr, L, R);
        // partition
        int[] bounds = partition3(arr, L, R, pivot);
        // compare
        // return
        if (idx >= bounds[0] && idx <= bounds[1]) {
            return pivot;
        } else if(idx < bounds[0]) {
            return bfprt(arr, L, bounds[0] - 1, idx);
        } else {
            return bfprt(arr, bounds[1] + 1, R, idx);
        }
    }

    public static int[] partition3(int[] arr, int L, int R, int pivot) {
        // init
        int less = L - 1;
        int more = R + 1;
        int cur = L;
        // compare
        while (cur < more) {
            if (arr[cur] < pivot) {
                swap(arr, ++less, cur++);
            } else if (arr[cur] > pivot) {
                swap(arr, --more, cur);
            } else {
                cur++;
            }
        }
        // return
        return new int[] {less + 1, more - 1};
    }

    public static int medianOfMedian(int[] arr, int L, int R) {
        // devide into groups of 5
        int offset = (R - L + 1) % 5 == 0 ? 0 : 1;  ///
        int groups = ((R - L + 1) / 5) + offset;    ///
        int[] medians = new int[groups];
        // get median of each group
        for(int team = 0; team < medians.length; team++) {
            // 0  5  10 15
            int idxFirst = L + team * 5;
            medians[team] = getMedian(arr, idxFirst, Math.min(R, idxFirst + 4));
        }
        // get median of medians
        return bfprt(medians, 0, medians.length - 1,groups/2);
    }

    public static int getMedian(int[] arr, int idxFirst, int idxLast) {
        insertSort(arr, idxFirst, idxLast);
        return arr[(idxLast + idxFirst)/2];  ////
    }

    public static void insertSort(int[] arr, int L, int R) {
        for (int i = L + 1; i <= R; i++) {
            for (int j = i - 1; j >= L && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) (Math.random() * maxSize) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTime = 100000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int k = (int) (Math.random() * arr.length) + 1;
            int ans1 = minKth1(arr, k);
            int ans2 = minKth2(arr, k);
            int ans3 = minKth3(arr, k);
            if (ans1 != ans2 || ans2 != ans3) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }

}
