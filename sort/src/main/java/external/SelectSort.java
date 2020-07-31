package external;

import java.util.Arrays;

/**
 *选择排序
 *  每次轮询拿到最小值，将它和数组头互换位置
 */
public class SelectSort {

    public static void main(String[] args) {
        int[] arr = {3,9,-1,10,20,4};
        selectSort(arr);
    }
    public static void selectSort(int[] arr) {

        for (int i=0;i<arr.length-1;i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j=i+1;j<arr.length;j++) {
                if (min>arr[j]) {
                    min = arr[j];
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
