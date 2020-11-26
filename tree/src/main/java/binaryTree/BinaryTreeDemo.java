package binaryTree;

public class BinaryTreeDemo {

    public static void main(String[] args) {

        BinaryTree binaryTree = new BinaryTree();

        //节点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        //暂时手写
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);

        //测试
        System.out.println("前序遍历");
        binaryTree.preOrder();

        System.out.println("中序遍历");
        binaryTree.infixOder();

        System.out.println("后续遍历");
        binaryTree.postOrder();

        System.out.println("删除3号节点");
        binaryTree.delNode(3);
        binaryTree.preOrder();


    }

}

//定义BinaryTree 二叉树
class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
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

class HeroNode {

    private int no;
    private String name;
    //默认null
    private HeroNode left;
    private HeroNode right;

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

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * 递归删除节点
     *  1 如果删除的节点是叶子节点 ，则删除节点
     *  2 如果删除的节点是非叶子节点，则删除该子树
     * @param no
     */
    public void delNode(int no) {
        if(null != this.left && this.left.no == no) {
            this.left = null;
            return;
        }
        if(null != this.right && this.right.no == no) {
            this.right = null;
            return;
        }
        //进行递归删除
        if (this.left != null) {
            this.left.delNode(no);
        }
        if (this.right != null ) {
            this.right.delNode(no);
        }
    }

    /**
     *前序遍历 根节点->左节点->右节点（就像小孩跑圈）
     */
    public void preOder() {
        //先输出父节点
        System.out.println(this);
        //递归向左子树遍历
        if (this.left != null) {
            this.left.preOder();
        }
        //递归向右子树遍历
        if (this.right !=null) {
            this.right.preOder();
        }
    }

    /**
     *中序遍历 左节点->根节点->右节点(树的投影）
     */
    public void infixOder() {

        //递归向左子树遍历
        if (this.left != null) {
            this.left.infixOder();
        }
        //输出父节点
        System.out.println(this);
        //递归向右子树遍历
        if (this.right !=null) {
            this.right.infixOder();
        }
    }

    /**
     *后序遍历 左节点->右节点->根节点（就像剪葡萄）
     */
    public void postOder() {

        //递归向左子树遍历
        if (this.left != null) {
            this.left.postOder();
        }
        //递归向右子树遍历
        if (this.right !=null) {
            this.right.postOder();
        }
        //输出父节点
        System.out.println(this);
    }
}