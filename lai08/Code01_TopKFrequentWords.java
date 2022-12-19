package lai08;
/*
[Question]
    Given a composition with different kinds of words, return a list of the top K most frequent words in the composition.
[Idea]
    use a hashmap to collect word frequency, then put all entries into a minHeap comparing entry's value, finally transfer all
    entry in minheap to result string array
[Notice]
    insert condition in minHeap, just imagine it is in the middle, will it be smaller or bigger than the top element
    how to write PriorityQueue correctly
    don't forget to poll before offer to maintain the heap
    Entry is the component of HashMap
    use freqMap.entrySet() to traverse entry in hashmap
    API hashmap.get()
    use hashmap.get() rather than .contains() to save time, when there is no aiming element, return should be null
[Complexity]
    Time  O(n + nlogk), push to hashmap n, push to minheap klogk + (n-k)logk
    Space O(n), use a hashmap
*/


import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class Code01_TopKFrequentWords {

    public static String[] topKFrequent(String[] combo, int k) {
        if (combo.length == 0) {
            return new String[0];
        }

        Map<String, Integer> freqMap = getFreqMap(combo);
        PriorityQueue<Entry<String, Integer>> minHeap = new PriorityQueue<>(new Comparator<Entry<String, Integer>>(){
            @Override
            public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {
                return e1.getValue() - e2.getValue();
            }
        });
        for (Entry<String, Integer> entry : freqMap.entrySet()) {
            if (minHeap.size() < k) {
                minHeap.offer(entry);
            } else if (entry.getValue() > minHeap.peek().getValue()) {
                minHeap.poll();
                minHeap.offer(entry);
            }
        }
        return getRes(minHeap);
    }


    private static Map<String, Integer> getFreqMap(String[] combo) {
        Map<String, Integer> freqMap = new HashMap<>();
        for (String s : combo) {
            if (freqMap.get(s) == null) {
                freqMap.put(s, 1);
            } else {
                freqMap.put(s, freqMap.get(s) + 1);
            }
        }
        return freqMap;
    }

    private static String[] getRes(PriorityQueue<Entry<String, Integer>> minHeap) {
        String[] res = new String[minHeap.size()];
        for (int i = minHeap.size() - 1; i >= 0; i--) {
            res[i] = minHeap.poll().getKey();
        }
        return res;
    }

    public static void main(String[] args) {
        String[] combo = {"a", "a", "b", "b", "b", "b", "c", "c", "c", "d"};
        int k = 2;
        String[] res = topKFrequent(combo, k);
        for (String item : res) {
            System.out.println(item);
        }
    }



}
