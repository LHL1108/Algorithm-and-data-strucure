package lai04;
import java.util.Deque;
import java.util.LinkedList;

/*
[Question]
    sort a stack with 2 stacks in total, no additional space, O(N^2) time complexity
    input [1 2 4 3  buffer[  -> input[4 3 2 1
[Idea]
    record the min element and its appearance times when transfer, put it into buffer, transfer rest elements back ,then do it iteratively,
    finally move all elements from buffer to input
[Construction]
    move elements from input to buffer
        record min
        record times
    move rest elements back
    put new min into buffer
    transfer from buffer to input
[Complexity]
    Time: O(N^2), for each minimum element, we move all elements 1 time
    Space: O(N), we use an extra stack space
*/

public class Code01_SortWith2Stacks {


    public static void sort(LinkedList s1) {
        if (s1 == null || s1.size() <= 1) {
            return;
        }
        LinkedList<Integer> s2 = new LinkedList<Integer>();
        sort(s1, s2);
    }
    public static void sort(Deque<Integer> input, Deque<Integer> buffer) {
        int tmp;
        int cnt = 0;
        while (!input.isEmpty()){
            int min = Integer.MAX_VALUE;
            while (!input.isEmpty()) {
                tmp = input.pollFirst();
                if (tmp < min) {
                    min = tmp;
                    cnt = 1;
                } else if (tmp == min) {
                    cnt++;
                }
                buffer.offerFirst(tmp);
            }
            while (!buffer.isEmpty() && buffer.peekFirst() >= min) {
                tmp = buffer.pollFirst();
                if (tmp > min) {
                    input.offerFirst(tmp);
                }
            }
            while (cnt > 0) {
                buffer.offerFirst(min);
                cnt--;
            }
        }
        while (!buffer.isEmpty()) {
            input.offerFirst(buffer.pollFirst());
        }
    }


    public static void main(String[] args) {
        LinkedList<Integer> test = new LinkedList<Integer>();
        test.addFirst(1);
        test.addFirst(3);
        test.addFirst(5);
        test.addFirst(4);
        test.addFirst(2);
        sort(test);

        while (test.size() != 0) {
            System.out.println(test.pollFirst());
        }
    }
}
