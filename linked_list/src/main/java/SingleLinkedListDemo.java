import java.util.Stack;

/**
 * 单向链表
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {
        //test
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero4);
//        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero4);
//        singleLinkedList.addByOrder(hero3);
        singleLinkedList.list();
        //更新
        HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟~~");
        singleLinkedList.update(newHeroNode);
        System.out.println();
        singleLinkedList.list();
        //删除
        System.out.println();
//        singleLinkedList.del(1);
//        singleLinkedList.del(4);
        singleLinkedList.list();
        //练习 1
        System.out.println("\n有效的节点个数：");
        System.out.println(getLength(singleLinkedList.getHead()));
        //新浪面试题：
        HeroNode res = findLastIndexNode(singleLinkedList.getHead(), 2);
        System.out.println("\n查找单链表中的倒数第K个节点：");
        System.out.println(res);
        //腾讯面试题：
        reverseList(singleLinkedList.getHead());
        System.out.println("\n单链表的反转：");
        singleLinkedList.list();
        //百度面试题：
        System.out.println("\n链表逆序打印：");
        reversePrint(singleLinkedList.getHead());
        //练习题:
        System.out.println("\n将两个链表有序合并:");
        SingleLinkedList singleLinkedList2 = new SingleLinkedList();
        singleLinkedList2.addByOrder(hero1);
//        singleLinkedList2.addByOrder(hero2);
//        singleLinkedList2.addByOrder(hero4);
        singleLinkedList2.addByOrder(hero3);
        SingleLinkedList singleLinkedList1 = mergeList(singleLinkedList, singleLinkedList2);
        singleLinkedList1.list();

    }

    /**
     * 练习 1
     * 获取单链表的有效节点个数，去除头节点
     *
     * @param head 头节点
     * @return 有效个数
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            return 0;
        }
        int length = 0;
        HeroNode temp = head.next;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }

    /**
     * 新浪面试题：
     * 查找单链表中的倒数第K个节点
     * 1.编写一个方法，接受head这个节点，同时接收index
     * 2.index 表示倒数第index个节点
     * 3.获取链表总长getlength,得到size后，遍历到（size-index)个，就可以了
     */
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        if (head.next == null) {
            return null;
        }
        int size = getLength(head);
        //验证index
        if (index <= 0 || index > size) {
            return null;
        }
        HeroNode temp = head.next;
        for (int i = 0; i < size - index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    /**
     * 腾讯面试题：
     * 单链表的反转
     * 1.定义一个节点reverseHead = new HeroNOde();
     * 2.从头到尾遍历原来的链表，每遍历一个，就将其取出，放在新的链表的最前端
     * 3.原来链表的head.niet = reverseHead.next
     */
    public static void reverseList(HeroNode head) {
        if (head.next == null || head.next.next == null) {
            return;
        }
        //定义辅助指针
        HeroNode cur = head.next;
        //指向当前节点的下一个节点
        HeroNode next = null;
        HeroNode reverseHead = new HeroNode(0, "", "");
        //遍历原来的来的链表
        while (cur != null) {
            //先暂时保存当前节点的下一个几点
            next = cur.next;
            cur.next = reverseHead.next;
            reverseHead.next = cur;
            //后移
            cur = next;
        }
        //将head.next 指向reverseHead.next,实现单链表的反转
        head.next = reverseHead.next;
    }

    /**
     * 百度面试题：
     * 链表的反向打印，使用栈的方式
     */
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            return;
        }
        Stack<HeroNode> stack = new Stack();
        HeroNode cur = head.next;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        //将栈中的节点出栈打印
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    /**
     * 练习题: 做错了
     * 将两个链表有序合并，输出
     */
    public static SingleLinkedList mergeList(SingleLinkedList node1, SingleLinkedList node2) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        HeroNode cur1 = node1.getHead().next;
        HeroNode temp = singleLinkedList.getHead();
        HeroNode next = null;
        //如果node1为空则直接返回node2
        if (cur1 == null) {
            singleLinkedList = node2;
            return singleLinkedList;
        }
        while (cur1 != null) {
            HeroNode cur2 = node2.getHead().next;
            while (cur2 != null) {
                if (cur1.no < cur2.no) {
                    singleLinkedList = addNode(singleLinkedList,cur2);
                }
                cur2 = cur2.next;
            }
            singleLinkedList = addNode(singleLinkedList,cur1);
            cur1 = cur1.next;
        }
        return singleLinkedList;
    }

    /**
     * 添加对应的节点到末尾
     * @return
     */
    public static SingleLinkedList addNode(SingleLinkedList resource,HeroNode node){
        HeroNode temp = resource.getHead();
        HeroNode temp2 = new HeroNode(0,"","");
        temp2 = node;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp2.next = null;
        temp.next = temp2;
        return resource;
    }
}

/**
 * 定义singleLinkedList 管理英雄
 */
class SingleLinkedList {
    //初始化头节点，头节点不存放具体数据，不动
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    /**
     * 添加节点,将最后节点的next指向当前节点
     */
    public void add(HeroNode heroNode) {
        //因为头节点不能动，所以使用一个辅助接点
        HeroNode temp = head;
        //遍历链表，找到最后的节点
        while (true) {
            //为空为最后
            if (temp.next == null) {
                break;
            }
            //没有则下一个
            temp = temp.next;
        }
        //将新增的节点放到后面，添加成功
        temp.next = heroNode;
    }

    /**
     * 第二中添加方式，按照no顺序添加
     * 添加的编号存在则报错
     */
    public void addByOrder(HeroNode heroNode) {
        //头节点不能动，使用辅助指针temp,找到要加入位置的前一个节点
        HeroNode temp = head;
        //标识添加的编号是否存在
        boolean flag = false;
        while (true) {
            //heroNode no位于最后
            if (temp.next == null) {
                break;
            }
            //heroNode no位于最前
            if (temp.next.no > heroNode.no) {
                break;
            } else if (temp.next.no == heroNode.no) {
                flag = true;
                break;
            }
            //后移
            temp = temp.next;
        }
        if (flag) {
            //编号存在，不能添加
            System.out.printf("%d编号存在，不能添加\n", heroNode.no);
        } else {
            //新节点插入链表
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    /**
     * 修改节点，no不可以修改
     */
    public void update(HeroNode heroNode) {
        if (head.next == null) {
            System.out.println("链表为空~");
        }
        //找到需要修改的节点，根据no
        HeroNode temp = head;
        //表示是否找到
        boolean flag = false;
        while (true) {
            //链表遍历完
            if (temp == null) {
                break;
            }
            if (temp.no == heroNode.no) {
                //找到了
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //是否找到了
        if (flag) {
            temp.name = heroNode.name;
            temp.nickname = heroNode.nickname;
        } else {
            System.out.printf("没有找到编号为%d的英雄\n", heroNode.no);
        }
    }

    /**
     * 删除节点，head不动，辅助指针temp
     */
    public void del(int no) {
        HeroNode temp = head;
        //是否找到no节点
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no) {
                //找到待删除节点的前一个节点
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.println("要删除的这个节点不存在~");
        }
    }

    /**
     * 显示链表
     */
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //头节点不能动，需要辅助节点
        HeroNode temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            //输出节点
            System.out.println(temp);
            //节点后移
            temp = temp.next;
        }
    }
}

/**
 * 定义heroNode,每个heroNode对象就是一个节点
 */
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    //指向下一个节点
    public HeroNode next;

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}