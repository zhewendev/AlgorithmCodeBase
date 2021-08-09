package com.zhewen.algorithmscollection.dualpointer;

/**
 * 左右指针经典问题
 */
public class LeftAndRightPointer {

    /**
     * 反转数组
     * @param nums
     */
    public void reverse(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int temp = nums[right];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }
}
