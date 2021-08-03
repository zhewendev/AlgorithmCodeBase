package com.zhewen.algorithmscollection;

public class TempTest {
    public static void main(String[] args) {
        int[] array = {4, 2, 8, 9, 5, 7, 6, 1, 3, 10};
        int[] array1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

//        System.out.println("数组为" + Arrays.toString(array));
        System.out.println("索引为" );
    }

    private static void recQuickSort(int[]array, int left,int right) {
        if (left > right) {
            int partition = partitionInt(array,left,right);
            recQuickSort(array,left,partition - 1);
            recQuickSort(array,partition + 1, right);
        }
    }

    private static int partitionInt(int[] array, int left,int right) {
        int i = left;
        int j = right + 1;
        int pivot = array[left];
        while (true) {
            while (i < right && array[++i] < pivot);
            while (j > 0 && array[--j] > pivot);
            if (i >= j) {
                break;
            } else {
                swap(array,i,j);
            }
        }
        swap(array,left,j);
        return j;
    }




    private static void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }
}
