import java.util.Scanner;

public class SingleListStackDemo {

    public static void main(String[] args) {
        SingleListStack stack = new SingleListStack();
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

//单向链表实现 栈
class SingleListStack {

    private Node top = null;

    //栈空
    public boolean isEmpty() {
        return top == null;
    }

    //入栈
    public void push(int value) {
        Node newNode = new Node(value, null);
        Node temp = top;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        if (temp == null) {
            top = newNode;
        }else {
            temp.next = newNode;
        }
    }

    //出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空，没有数据~");
        }
        Node temp = top;
        while (true) {
            if (temp.next.next == null) {
                break;
            }
            temp = temp.next;
        }
        int value = temp.next.data;
        temp.next = null;
        return value;
    }

    //遍历栈
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据~");
            return;
        }
        Node temp = top;
        while(true) {
            if (temp == null) {
                break;
            }
            System.out.printf("栈：%d\n",temp.data);
            temp = temp.next;
        }
    }
}

class Node {
    public int data;
    public Node next;

    public Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                '}';
    }
}