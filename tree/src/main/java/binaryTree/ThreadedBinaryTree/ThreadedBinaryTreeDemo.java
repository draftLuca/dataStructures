package binaryTree.ThreadedBinaryTree;

import java.util.HashMap;

public class ThreadedBinaryTreeDemo {

    public static void main(String[] args) {
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dim");

        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        //测试线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();

        //测试10号节点为例
        HeroNode left = node5.getLeft();
        System.out.println("10号节点前驱节点"+left);
        System.out.println("10号节点后继节点"+node5.getRight());

        //遍历线索化二叉树，不能使用原有二叉树的遍历了
        System.out.println("遍历线索化二叉树：");
        threadedBinaryTree.threadedList();


    }
}


class HeroNode {

    private int no;
    private String name;
    //默认null
    private HeroNode left;
    private HeroNode right;

    //如果leftType == 0 便是指向的是左子树，如果为1表示指向前驱节点
    private int leftType;
    //如果rightType == 0 便是指向的是右子树，如果为1表示指向后继节点
    private int rightType;

    public HeroNode(int no, String name) {
        super();
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * 递归删除节点
     * 1 如果删除的节点是叶子节点 ，则删除节点
     * 2 如果删除的节点是非叶子节点，则删除该子树
     *
     * @param no
     */
    public void delNode(int no) {
        if (null != this.left && this.left.no == no) {
            this.left = null;
            return;
        }
        if (null != this.right && this.right.no == no) {
            this.right = null;
            return;
        }
        //进行递归删除
        if (this.left != null) {
            this.left.delNode(no);
        }
        if (this.right != null) {
            this.right.delNode(no);
        }
    }

    /**
     * 前序遍历 根节点->左节点->右节点（就像小孩跑圈）
     */
    public void preOder() {
        //先输出父节点
        System.out.println(this);
        //递归向左子树遍历
        if (this.left != null) {
            this.left.preOder();
        }
        //递归向右子树遍历
        if (this.right != null) {
            this.right.preOder();
        }
    }

    /**
     * 中序遍历 左节点->根节点->右节点(树的投影）
     */
    public void infixOder() {

        //递归向左子树遍历
        if (this.left != null) {
            this.left.infixOder();
        }
        //输出父节点
        System.out.println(this);
        //递归向右子树遍历
        if (this.right != null) {
            this.right.infixOder();
        }
    }

    /**
     * 后序遍历 左节点->右节点->根节点（就像剪葡萄）
     */
    public void postOder() {

        //递归向左子树遍历
        if (this.left != null) {
            this.left.postOder();
        }
        //递归向右子树遍历
        if (this.right != null) {
            this.right.postOder();
        }
        //输出父节点
        System.out.println(this);
    }
}

/**
 * 定义ThreadedBinaryTree 线索化二叉树
 */
class ThreadedBinaryTree {
    private HeroNode root;

    //为了实现线索化，需要创建一个指向当前节点的前驱节点的指针
    private HeroNode pre = null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //重载threadedNodes
    public void threadedNodes() {
        this.threadedNodes(root);
    }

    //遍历线索化二叉树
    public void threadedList() {
        //定义一个变量，存储当前遍历的节点，从root 开始
        HeroNode node = root;
        while(node != null) {
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            System.out.println(node);
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }

    }
    /**
     * 编写对二叉树线索化的方法 中序线索化
     * @param node
     */
    public void threadedNodes(HeroNode node) {
        if (node == null ) {
            return;
        }
        //1 线索化左子树
        threadedNodes(node.getLeft());
        //2 线索化当前节点
        if (node.getLeft() == null) {
            //让当前节点的左指针指向前驱节点
            node.setLeft(pre);
            node.setLeftType(1);
        }
        //处理后继节点
        if (pre != null && pre.getRight() == null) {
            //让前驱节点的右指针指向当前节点
            pre.setRight(node);
            pre.setRightType(1);
        }
        //每处理一个节点后，让当前节点是下一个节点的前驱节点
        pre = node;
        //3 线索化右子树
        threadedNodes(node.getRight());
    }

    //前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOder();
        }else {
            System.out.println("当前二叉树为空~");
        }
    }

    //中序遍历
    public void infixOder() {
        if (this.root != null) {
            this.root.infixOder();
        }else {
            System.out.println("当前二叉树为空~");
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOder();
        }else {
            System.out.println("当前二叉树为空~");
        }
    }

    public void delNode(int no) {
        if (root != null) {
            //如果只有一个root节点，判断root是否为要删除的节点
            if (root.getNo() == no) {
                root = null;
            } else {
                root.delNode(no);
            }
        }else {
            System.out.println("空树不能删除");
        }
    }
}
