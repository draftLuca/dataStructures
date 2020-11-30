package binaryTree;

/**
 * 顺序存储二叉树: 树 数组转换
 *  1 顺序存储二叉树只考虑完全二叉树
 *  2 第n个元素的左子节点为 2*n+1
 *  3 第n个元素的右子节点为 2*n+2
 *  4 第n个元素的父节点为 （n-1)/2
 *  5 n:表示二叉树中的第n个元素（按0开始编号）
 *
 */
public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        arrayBinaryTree.preOrder();
    }
}

//编写ArrayBinaryTree,实现顺序存储二叉树遍历
class ArrayBinaryTree {
    private int[] arr;//存储节点数组

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //重载
    public void preOrder() {
        preOrder(0);
    }
    /**
     * 编写一个方法，完成顺序存储二叉树的前序遍历
     * @param index 数组下标
     */
    public void preOrder(int index) {
        //如果数组为空，
        if (arr == null || arr.length ==0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        System.out.println(arr[index]);
        //向左递归遍历
        if ((index*2 +1) <arr.length) {
            preOrder(2*index+1);
        }
        //向右递归
        if (index*2+2<arr.length) {
            preOrder(2*index+2);
        }

    }
}