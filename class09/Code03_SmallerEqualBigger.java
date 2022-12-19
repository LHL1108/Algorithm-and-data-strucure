package class09;

public class Code03_SmallerEqualBigger {
    /*
    要求： 给定一阈值，将链表调整为小于阈值的放左边，等于放中间，大于放右边，返回调整后链表的头节点
    思路：
        1.容器法，使用数组，遍历节点放入数组中，使用快排的partition方法
          （初始化三区坐标，只要idx没有和大于区重合，小于则放小于区后，idx后移，等于idx后移，大于则放大于区前，idx不后移）
          将其调整为有序，最后连接所有元素。
        2.链表方法，遍历链表节点，根据情况分别放入小于区等于区大于区中，最后连接三区首尾巴
          最后连接三区首尾(小于区不为空，则小尾连等头，等尾看情况要不要变成小尾，等于区不为空，等尾连大头)，最后根据情况返回三个中第一个不为空的头
    易错点：
        在将节点放入三个区之前要先提前保存下个节点并将该节点断连
    */

    public static class Node {
        public int value;
        public Node next;
        public Node(int data) {
            this.value = data;
        }
    }


    public static void swap(Node[] nodearr, int i, int j){
        Node tmp = nodearr[i];
        nodearr[i] = nodearr[j];
        nodearr[j] = tmp;
    }


    public static void arrPartion(Node[] nodearr, int pivot){
        int small = -1;
        int idx = 0;
        int big = nodearr.length;

        while(idx < big) {
            if(nodearr[idx].value < pivot) {
                swap(nodearr, ++small, idx++);
            }else if(nodearr[idx].value == pivot) {
                idx++;
            }else {
                swap(nodearr, --big, idx);
            }
        }
    }


    public static Node listPartition1(Node head, int pivot) {
        if (head == null) {
            return null;
        }

        int len = 0;
        Node cur1 = head;
        while (cur1 != null) {
            len++;
            cur1 = cur1.next;
        }
        Node[] nodearr = new Node[len];

        Node cur2 = head;
        for (int i = 0; i < len; i++) {
            nodearr[i] = cur2;
            cur2 = cur2.next;
        }

        arrPartion(nodearr, pivot);

        for (int i = 1; i < len; i++) {
            nodearr[i-1].next = nodearr[i];
        }
        nodearr[len - 1].next = null;
        return nodearr[0];
    }


    public static Node listPartition2(Node head, int pivot){
        if (head == null) {
            return null;
        }
        Node sH = null;
        Node sT = null;
        Node eH = null;
        Node eT = null;
        Node mH = null;
        Node mT = null;

        Node cur = head;
        while (cur != null) {
            Node next = cur.next;
            cur.next = null;
            if (cur.value < pivot) {
                if (sH == null) {
                    sH = cur;
                    sT = cur;
                } else {
                    sT.next = cur;
                    sT = cur;
                }
            } else if(cur.value == pivot) {
                if (eH == null) {
                    eH = cur;
                    eT = cur;
                } else {
                    eT.next = cur;
                    eT = cur;
                }
            }else {
                if (mH == null) {
                    mH = cur;
                    mT = cur;
                } else {
                    mT.next = cur;
                    mT = cur;
                }
            }
            cur = next;
        }
        if(sT != null) {
            sT.next = eH;
            eT = eT == null ? sT : eT;
        }
        if (eT != null) {
            eT.next = mH;
        }
        return sH != null ? sH : (eH != null ? eH : mH);
    }

////////////////////////////////////////////////////////////////


    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        printLinkedList(head1);
        //head1 = listPartition1(head1, 5);
        head1 = listPartition2(head1, 5);
        printLinkedList(head1);

    }

}
