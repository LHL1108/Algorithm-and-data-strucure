package lai04;
/*
[Question]
    use three Stacks to implement a Deque
[Idea]
     when stack2 is empty and need to poll or peek last, move elements from 654321][ to like this 654][321
     offerFirst, always push to s1
     offerLast, push to s2
     pollFirst, poll from s1
     pollLast, poll from s2
     peekFirst, peek from s1
     peekLast, peek from s2
     size, size
     isEmpty, size == 0
*/

import java.util.Deque;
import java.util.LinkedList;

public class Code05_DequeByThreeStacks {
    public static class MyDeque {
        int size;
        Deque<Integer> s1;
        Deque<Integer> s2;
        Deque<Integer> s3;

        public MyDeque() {
            s1 = new LinkedList();
            s2 = new LinkedList();
            s3 = new LinkedList();
        }

        public void offerFirst(int e) {
            s1.offerFirst(e);
            size++;
        }

        public void offerLast(int e) {
            s2.offerFirst(e);
            size++;
        }

        public Integer pollFirst() {
            if (size == 0) {
                return null;
            } else {
                if (s1.isEmpty()) {
                    for (int i = 0; i < size/2; i++) {
                        s3.offerFirst(s2.pollFirst());
                    }
                    for (int i = size/2; i < size; i++) {
                        s1.offerFirst(s2.pollFirst());
                    }
                    for (int i = 0; i < size/2; i++) {
                        s2.offerFirst(s3.pollFirst());
                    }
                }
                size--;
                return s1.pollFirst();
            }
        }

        public Integer pollLast() {
            if (size == 0) {
                return null;
            } else {
                if (s2.isEmpty()) {
                    for (int i = 0; i < size/2; i++) {
                        s3.offerFirst(s1.pollFirst());
                    }
                    for (int i = size/2; i < size; i++) {
                        s2.offerFirst(s1.pollFirst());
                    }
                    for (int i = 0; i < size/2; i++) {
                        s1.offerFirst(s3.pollFirst());
                    }
                }
                size--;
                return s2.pollFirst();
            }
        }

        public Integer peekFirst() {
            if (size == 0) {
                return null;
            } else {
                if (s1.isEmpty()) {
                    for (int i = 0; i < size/2; i++) {
                        s3.offerFirst(s2.pollFirst());
                    }
                    for (int i = size/2; i < size; i++) {
                        s1.offerFirst(s2.pollFirst());
                    }
                    for (int i = 0; i < size/2; i++) {
                        s2.offerFirst(s3.pollFirst());
                    }
                }
                return s1.peekFirst();
            }
        }

        public Integer peekLast() {
            if (size == 0) {
                return null;
            } else {
                if (s2.isEmpty()) {
                    for (int i = 0; i < size/2; i++) {
                        s3.offerFirst(s1.pollFirst());
                    }
                    for (int i = size/2; i < size; i++) {
                        s2.offerFirst(s1.pollFirst());
                    }
                    for (int i = 0; i < size/2; i++) {
                        s1.offerFirst(s3.pollFirst());
                    }
                }
                return s2.peekFirst();
            }
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }
    }


    public static void main(String[] args) {
        MyDeque d = new MyDeque();
//        d.offerFirst(1);
//        d.offerFirst(2);
//        d.offerFirst(3);
//
//        // 1 3 1 2 true
//        System.out.println(d.pollLast());
//        System.out.println(d.pollFirst());
//        System.out.println(d.size());
//        System.out.println(d.pollLast());
//        System.out.println(d.isEmpty());
        System.out.println(d.peekFirst());
        d.offerLast(74);
        System.out.println(d.peekFirst());

    }



}
