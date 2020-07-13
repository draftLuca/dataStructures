/**
 * 约瑟夫问题DEMO:
 *      小孩出圈问题
 */
public class Josepfu {
    public static void main(String[] args) {
        //test
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(10);
        circleSingleLinkedList.showBoy();
        //测试小孩出圈
        circleSingleLinkedList.countBoy(5,2,5);
    }
}

/**
 * 节点
 */
class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}

/**
 * 单向环形链表
 */
class CircleSingleLinkedList {
    //first节点
    private Boy first = new Boy(-1);

    //添加小孩节点，构建一个环形链表
    public void addBoy(int nums) {
        //验证nums
        if (nums < 1) {
            System.out.println("nums的值不正确~");
            return;
        }
        //辅助指针，帮助构建链表
        Boy curBoy = null;
        //使用for创建环形链表
        for (int i = 1; i <= nums; i++) {
            //根据编号，创建小孩节点
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                first.setNext(first);
                curBoy = first;
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    //遍历环形链表
    public void showBoy() {
        if (first == null) {
            System.out.println("链表为空~");
            return;
        }
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号%d\n", curBoy.getNo());
            if (curBoy.getNext() == first) {
                //遍历完毕
                break;
            }
            curBoy = curBoy.getNext();
        }
    }

    /**
     * 根据用户的输入，计算小孩出圈的顺序
     *
     * @param starNo   表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums     表示最初有多少小孩在圈中
     */
    public void countBoy(int starNo, int countNum, int nums) {
        if (first == null || starNo < 1 || starNo > nums) {
            System.out.println("参数输入有误~");
            return;
        }
        Boy helper = first;
        while (true) {
            if (helper.getNext() == first) {
                //说明helper 指向了最后的小孩
                break;
            }
            helper = helper.getNext();
        }
        //报数前，先让first 和helper 移动k-1次
        for (int j = 0; j < starNo - 1; j++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //当小孩报数时，让first 和 helper 指针同时移动 m-1次，然后出圈
        //这里是一个循环操作，直到圈中只有一个节点
        while (true) {
            if (helper == first) {
                //圈中只有一个节点
                break;
            }
            //让 fist helper 指针同时移动cuntnum -1
            for (int j = 0; j < countNum - 1; j++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.printf("小孩%d出圈：\n",first.getNo());
            //将出圈的小孩从 循环链表中移除
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号%d\n",first.getNo());
    }
}