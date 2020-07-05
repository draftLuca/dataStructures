import java.util.Scanner;

/**
 * 环形数组实现队列
 */
public class CircleArrayQueue {

    public static void main(String[] args) {
        CircleQueue arrayQueue = new CircleQueue(4);
        char key = ' '; //接受用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出队列");
            System.out.println("a(add): 添加队列");
            System.out.println("g(get): 获取队列头");
            System.out.println("h(head): 显示队列头");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                case 'a':
                    System.out.println("请输入一个数，添加到对列！");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int queue = arrayQueue.getQueue();
                        System.out.printf("取出的数据是：%d\n", queue);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 'h':
                    try {
                        int i = arrayQueue.headQueue();
                        System.out.printf("队列头的数据是：%d\n", i);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;
            }
        }
        System.out.println("退出~");
    }
}

class CircleQueue {
    private int maxSize;
    private int front; //头
    private int rear; //尾
    private int[] arr; //用于存放数据，模拟队列

    public CircleQueue() {
    }

    public CircleQueue(int maxSize) {
        this.maxSize = maxSize;
        this.arr = new int[maxSize];
        this.front = 0; //指向队列头的前一个位置
        this.rear = 0; //指向队列尾数据
    }

    //判断队列是否满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    //队列是否空
    public boolean isEmpty() {
        return rear == front;
    }

    //添加队列
    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列已满~");
            return;
        }
        //直接将数据加入
        arr[rear] = n;
        //将rear 后移  ，考虑取模
        rear = (rear + 1) % maxSize;
    }

    //获取队列头数据
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空！");
        }
        //这里需要分析出front是指向对列的第一个元素
        //1.先把front对应的值保留到一个临时变量
        //2.front 后移，取模
        //3.返回临时变量
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    //展示队列所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("该队列为空！，没有数据！");
            return;
        }
        //从front开始遍历，
        //
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d] = %d\n", i%maxSize, arr[i%maxSize]);
        }
    }

    //求出当前丢列的有效数据个数
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    //显示队列头数据
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空！");
        }
        return arr[front];
    }
}
