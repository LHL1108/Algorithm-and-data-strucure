package class09;

import java.util.Stack;

public class Code02_IsPalindromeList {
    /*
    要求：判断一串链表的值是否为回文结构
    思路：
        方法1：使用栈结构，把所有元素放入，再从头遍历，和栈的弹出元素做比对，占用额外空间n
        方法2：使用栈结构，找到元素中点后一个元素或后中点，将后续元素压入栈，再从头遍历链表，和栈中元素做比对
        方法3：找到元素中点，将后半部分链表反转，再分别从头尾遍历链表，做比对，最后将链表结构复原
        一步一步来
    */
    //定义链表结构
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static boolean isPalindrome1(Node head) {
        // 创建栈结构
        Stack<Integer> s = new Stack<>();
        // 将链表中的所有元素放入栈
        Node cur1 = head;
        while (cur1 != null) {
            s.push(cur1.value);
            cur1 = cur1.next;
        }
        // 将链表元素和栈进行比对
        Node cur2 = head;
        while (cur2 != null) {
            if (cur2.value != s.pop()) {
                return false;
            }
            cur2 = cur2.next;
        }
        return true;
    }

    public static boolean isPalindrome2(Node head) {
        Stack<Integer> stack = new Stack<>();
        if (head == null || head.next == null) {
            return true;
        }
        Node slow = head.next;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        while (slow != null) {
            stack.push(slow.value);
            slow = slow.next;
        }
        while (!stack.isEmpty()) {
            if (stack.pop() != head.value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    // 调整链表结构
    public static boolean isPalindrome3(Node head) {
        /*
        找到中点或后中点
        将后半部分链表逆序
            确定初始位置
            改变指向
            更新节点
        从两头开始遍历对比
            如果不同，则跳出循环，结果为false
        恢复链表结构
        返回结果
        */
        if (head == null || head.next == null) {
            return true;
        }
        Node slow = head.next;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node n1 = null;
        Node n2 = slow;
        Node n3 = slow.next;
        while (n2 != null) {
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }
        boolean res = true;
        Node tail = n1;
        Node cur = head;
        while (n1 != null) {
            if (cur.value != n1.value) {
                res = false;
                break;
            }
            cur = cur.next;
            n1 = n1.next;
        }
        n1 = null;
        n2 = tail;
        n3 = n2.next;
        while (n2 != null) {
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }
        return res;
    }


    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Node head = null;
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(2);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

    }

}
