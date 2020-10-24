import java.util.Scanner;

public class HashTableDemo {
    public static void main(String[] args) {
        //创建哈希表
        HashTab hashTab = new HashTab(7);

        //写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add:添加雇员");
            System.out.println("list:显示雇员");
            System.out.println("find:查找雇员");
            System.out.println("delete:删除雇员");
            System.out.println("exit:退出");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id:");
                    int id = scanner.nextInt();
                    System.out.println("输入名字：");
                    String name = scanner.next();
                    //创建雇员
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "exit":
                    scanner.close();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    int i = scanner.nextInt();
                    hashTab.findEmpById(i);
                    break;
                case "delete":
                    System.out.println("请输入要删除的id:");
                    int deleteId = scanner.nextInt();
                    hashTab.deleteEmpById(deleteId);
                    break;
                default:
                    break;
            }
        }


    }
}

//创建HashTable 管理多条链表
class HashTab {
    private EmpLinkedList[] empLinkedArray;
    private int size;

    //构造
    public HashTab(int size) {
        this.size = size;
        //初始化
        empLinkedArray = new EmpLinkedList[size];
        //注意，分别初始化每条链表
        for (int i = 0; i < size; i++) {
            empLinkedArray[i] = new EmpLinkedList();
        }
    }

    //添加雇员
    public void add(Emp emp) {
        //根据员工的id得到该员工应该添加到哪条链表
        int empLinkedListNO = hashFun(emp.id);
        //将emp添加到对应链表中
        empLinkedArray[empLinkedListNO].add(emp);
    }

    //遍历所有的链表hashTable
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedArray[i].list(i);
        }
    }

    //根据输入的id,查找雇员
    public void findEmpById(int id) {
        //使用散列函数确定那条链表
        int empLinkedListNO = hashFun(id);
        Emp emp = empLinkedArray[empLinkedListNO].findEmpById(id);
        if (emp != null) {
            System.out.printf("在第" + empLinkedListNO + "条链表中找到雇员id = %d\n", empLinkedListNO);
        } else {
            System.out.println("哈希表中没有找到该雇员~");
        }
    }

    //根据输入的id,删除雇员
    public void deleteEmpById(int id) {
        int empLinkedListNO = hashFun(id);
        empLinkedArray[empLinkedListNO].delete(id);
    }

    //编写一个散列函数，取模法
    public int hashFun(int id) {
        return id % size;
    }
}

//表示一个雇员
class Emp {
    public int id;
    public String name;
    //next 默认为空
    public Emp next;

    public Emp() {
    }

    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}

//创建一个EmpLinkedList ,表示链表
class EmpLinkedList {
    //头指针，指向第一个Emp,head直接指向一个emp
    private Emp head; //默认null

    /**
     * 添加雇员到链表
     * 说明
     * 1，假定，当添加雇员时 ，id自增长，id从小到大
     * 因此我们直接将该雇员直接添加到最后
     *
     * @param emp
     */
    public void add(Emp emp) {
        //如果是第一个e'm'p
        if (head == null) {
            head = emp;
            return;
        }
        //如果不是第一个emp,则使用一个辅助指针，定位到最后
        Emp curEmp = head;
        while (true) {
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
        //退出时直接加入到最后
        curEmp.next = emp;
    }

    /**
     * 遍历链表
     */
    public void list(int i) {
        if (head == null) {
            System.out.printf("当前第%d链表为空~\n", i);
            return;
        }
        System.out.printf("当前第%d的链表信息为：", i);
        Emp curEmp = head;
        while (true) {
            System.out.printf("=> id = %d name = %s\n", curEmp.id, curEmp.name);
            if (curEmp.next == null) {
                break;
            }
            //后移
            curEmp = curEmp.next;
        }
    }

    /**
     * 根据id查找雇员
     * 没查到返回null
     */
    public Emp findEmpById(int id) {
        //判断链表是否为空
        if (head == null) {
            return null;
        }
        //辅助指针
        Emp curEmp = head;
        while (true) {
            if (curEmp.id == id) {
                break;
            }
            if (curEmp.next == null) {
                //当前链表没有找到
                curEmp = null;
                break;
            }
            //后移
            curEmp = curEmp.next;
        }
        return curEmp;
    }

    /**
     * 删除emp
     */
    public void delete(int id) {
        if (head == null) {
            System.out.println("要删除的雇员不存在~");
            return;
        }
        Emp curEmp = head;
        while (true) {
            //如果第一个元素就是要删除的元素
            if (curEmp.id == id) {
                head = null;
                System.out.printf("id为%d的雇员删除成功\n", id);
                break;
            }
            //没找到
            if (curEmp.next == null) {
                System.out.printf("没有找到id为%d的雇员~\n", id);
                break;
            }
            if (curEmp.next.id == id) {
                Emp temp = curEmp.next;
                curEmp.next = curEmp.next.next;
                temp.next = null;
                System.out.printf("id为%d的雇员删除成功\n", id);
                break;
            }
            curEmp = curEmp.next;
        }
    }
}