package external;

import java.util.Arrays;

/**
 * 插入排序
 *  将数组分为一个排序好的子数组1，和一个原始未经过排序的子数组2，依次将数组2的数放到数组1中
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] arr = {3, 9, -1, 10, 20, 4};
        insertSort(arr);
}

    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            //数组2中待排序的数
            int insertVal = arr[i];
            //数组1中最后一个元素，即数组1最大的元素，第一个待比较元素的下标
            int insertIndex = i - 1;
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            arr[insertIndex + 1] = insertVal;
        }
        System.out.println(Arrays.toString(arr));
    }
}
