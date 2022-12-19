package class17;
/*
要求： 使用递归方法翻转栈中元素
思路
    利用递归函数的特性，就是现解开后面的递归，才能运行前面的递归，所以后面的递归函数现运行，所以后面的压入
    先运行，所以后面抽出的元素先压入，后面抽出的是栈顶元素，所以栈顶元素先压入，便完成了倒置

    而抽出栈底元素同样运用了递归的特性，首先它分为两种情况，就是栈中的元素是一个或者多个，一个就直接返回
    多个就直接运行递归函数，保存last,然后将先前弹出的元素放回恢复现场。

代码
    递归方法
        栈为空
            直接返回
        抽出栈底元素
        递归自身
        压入抽出元素

    抽出栈底元素方法
        弹出栈顶元素
            如果栈空
                返回结果
            否则
                迭代该函数直至取出最后一个元素
                将栈压入结果
*/

import java.util.Stack;

public class Code05_ReverseStackUsingRecursive {

    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return ;
        }

        int last = getBottomElement(stack);
        reverse(stack);
        stack.push(last);
    }

    public static int getBottomElement(Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            int last = getBottomElement(stack);
            stack.push(result);
            return last;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        reverse(stack);
        while(!stack.isEmpty()) {
            System.out.println(stack.pop());
        }

    }

}
