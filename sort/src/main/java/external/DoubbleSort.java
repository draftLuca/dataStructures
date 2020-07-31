package external;

import java.util.Arrays;

/**
 * 冒泡排序
 *  相邻两个数比较，每一次都将最大的数移动到末尾
 */
public class DoubbleSort {

    public static void main(String[] args) {
        int[] arr = {3,9,-1,10,20};
        //临时变量
        int temp = 0;
        //优化项，如果一趟下来都没有交换位置，则以排好
        Boolean flag;
        for (int j=0;j<arr.length;j++) {
            flag = false;
            for (int i=0;i<arr.length-1-j;i++) {
                if (arr[i] > arr[i+1]) {
                    temp = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = temp;
                    flag = true;
                }
            }
            if (flag) {
                break;
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
