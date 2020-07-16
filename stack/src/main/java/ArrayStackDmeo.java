
import java.util.Scanner;

public class ArrayStackDmeo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(4);
        String key ="";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        while(loop) {
            System.out.println("show: 表示显示栈");
            System.out.println("exit: 退出程序");
            System.out.println("push: 添加");
            System.out.println("pop: 出栈");
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                case "push":
                    System.out.println("请输入一个数：");
                    int i = scanner.nextInt();
                    stack.push(i);
                    break;
                case "pop":
                    try {
                        int pop = stack.pop();
                        System.out.printf("出栈的数据是%d的\n",pop);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出了~");
    }
}

//数组实现 栈
class ArrayStack {
    private int maxSize;
    private int[] stack;
    //栈顶，初始化-1
    private int top = -1;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈以满~");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空，没有数据~");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //遍历栈
    public void list() {
        if(isEmpty()) {
            System.out.println("栈空，没有数据~");
            return;
        }
        for (int i=top;i>=0;i--) {
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }
}