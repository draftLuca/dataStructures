/**
 * 双向列表Demo
 */
public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        //test
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
//        doubleLinkedList.add(hero1);
//        doubleLinkedList.add(hero2);
//        doubleLinkedList.add(hero3);
//        doubleLinkedList.add(hero4);

        doubleLinkedList.addByOrder(hero3);
        doubleLinkedList.addByOrder(hero4);
        doubleLinkedList.addByOrder(hero2);
        doubleLinkedList.addByOrder(hero1);

        doubleLinkedList.list();

        //修改
        HeroNode2 newHeroNode = new HeroNode2(4, "公孙胜", "入云龙");
        doubleLinkedList.update(newHeroNode);
        System.out.println("\n修改后得链表情况：");
        doubleLinkedList.list();

        //删除
        doubleLinkedList.del(3);
        System.out.println("\n删除后的链表情况：");
        doubleLinkedList.list();
    }
}

/**
 * 双向列表
 */
class DoubleLinkedList {
    //初始化头节点，头节点不存放具体数据，不动
    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }

    /**
     * 添加节点,将最后节点的next指向当前节点
     */
    public void add(HeroNode2 heroNode) {
        //因为头节点不能动，所以使用一个辅助接点
        HeroNode2 temp = head;
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
        heroNode.pre = temp;
    }

    /**
     * 第二中添加方式，按照no顺序添加
     * 添加的编号存在则报错
     */
    public void addByOrder(HeroNode2 heroNode) {
        //头节点不能动，使用辅助指针temp,找到要加入位置的前一个节点
        HeroNode2 temp = head;
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
            if (temp.next != null) {
                temp.next.pre = heroNode;
                heroNode.next = temp.next;
                temp.next = heroNode;
                heroNode.pre = temp;

            } else {
                //新增节点新增在链表最后
                temp.next = heroNode;
                heroNode.pre = temp;
            }
        }
    }

    /**
     * 修改节点，no不可以修改
     */
    public void update(HeroNode2 heroNode) {
        if (head.next == null) {
            System.out.println("链表为空~");
        }
        //找到需要修改的节点，根据no
        HeroNode2 temp = head;
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
     * 对于双向链表，不需要借助辅助节点，直接找到删除
     */
    public void del(int no) {

        if (head.next == null) {
            System.out.println("链表为空，不能删除！");
            return;
        }
        HeroNode2 temp = head.next;
        //是否找到no节点
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == no) {
                //找到待删除节点的前一个节点
                flag = true;
                break;
            }
            //后移
            temp = temp.next;
        }
        if (flag) {
            temp.pre.next = temp.next;
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
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
        HeroNode2 temp = head.next;
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
class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    //指向下一个节点
    public HeroNode2 next;
    //指向前一个节点
    public HeroNode2 pre;

    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}