package class07;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/*
要求：实现一个加强堆，包含记录每个元素位置的indexMap,
     具备isEmpty, size, contains, peek, pop, remove, resign, getAllElements等功能
思路：
    属性：heap, heapSize, indexMap

易错：
    定义类的属性用private
*/
public class HeapGreater<T> {
    private ArrayList<T> heap;
    private int heapSize;
    private HashMap<T, Integer> indexMap;
    private Comparator<? super T> comp;

    public HeapGreater(Comparator<T> c){
        heap = new ArrayList<>();
        heapSize = 0;
        indexMap = new HashMap<>();
        comp = c;
    }

    public boolean isEmpty(){
        return heapSize == 0;
    }

    public int size() {
        return heapSize;
    }

    public boolean contains(T obj) {
        return indexMap.containsKey(obj);
    }

    public T peek() {
        return heap.get(0);
    }

    public void swap(int i, int j){
        // 更新堆
        T obj1 = heap.get(i);
        T obj2 = heap.get(j);
        heap.set(j, obj1);
        heap.set(i, obj2);
        // 更新索引表
        indexMap.put(obj1, j);
        indexMap.put(obj2, i);
    }

    public void heapify(int idx) {
        // 算出左孩子
        int left = (idx * 2) + 1;
        // 只要左孩子不越界进行循环
        while (left <= heapSize - 1) {
            // 如果右孩子不越界且右孩子大于左孩子，大的是右，否则是左 heap.get(left + 1) > heap.get(left)
            int largerIdx = (left+1) < heapSize && comp.compare(heap.get(left + 1), heap.get(left)) < 0 ? left + 1 : left;
            // 比较大孩子和爹，确定最大值
            largerIdx = comp.compare(heap.get(idx), heap.get(largerIdx)) < 0 ? idx : largerIdx;
            // 爹最大，跳出循环
            if (largerIdx == idx) {
                break;
            }
            // 孩子大，交换元素，并更新下标
            swap(idx, largerIdx);
            idx = largerIdx;
            left = (idx * 2) + 1;
        }
    }

    public T pop() {
        // 记录堆顶并返回
        T ans = heap.get(0);
        // 调整堆
        // 交换堆顶与堆底元素
        swap(0, heapSize-1);
        // 调整堆的大小及indexMap
        heapSize--;
        indexMap.remove(ans);
        // 对堆顶元素进行heapify
        heapify(0);

        return ans;

    }

    public void heapInsert(int idx) {
        // 只要idx大于0
            // 计算爹
            // 如果没有爹大跳出循环
            // 比爹大就交换并更新下标
        while (idx > 0) {
            if (comp.compare(heap.get((idx - 1)/2), heap.get(idx)) < 0) {
                break;
            } else {
                swap((idx - 1) / 2, idx);
                idx = (idx - 1) / 2;
            }
        }
    }

    public void resign(T obj) {
        heapify(indexMap.get(obj));
        heapInsert(indexMap.get(obj));
    }

    public void remove(T obj) {
        // 在堆中移除
            // 将当前位置的值与末尾值交换
            // 调整堆的大小
            // 对空降元素做resign
        int idx = indexMap.get(obj);
        swap(idx, heapSize - 1);
        heapSize--;
        resign(heap.get(idx));

        // 在索引表中移除
        indexMap.remove(obj);
    }

    public List<T> getAllElements(){
        List<T> ans = new ArrayList<>();
        for (T e : heap) {
            ans.add(e);
        }
        return ans;
    }
}
