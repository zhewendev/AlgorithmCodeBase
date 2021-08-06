package com.zhewen.algorithmscollection.dynamicplanning;

import java.util.Arrays;

/**
 * NSum经典问题
 */
public class NSumProblem {
    /**
     * 请你返回 nums中能够凑出 target 的两个元素的值
     * 方法一：数组排序，然后双指针相向而行，复杂度较高
     * 方法二：数组排序，然后双指针相向而行，跳过重复元素
     * 方法三：使用哈希表处理
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        int left = 0;
        int right = n - 1;
        int[] res = new int[2];
        while(left < right) {
            int sum = nums[left] + nums[right];
            if (sum < target) {

            } else if (sum > target) {

            } else {

            }
        }
    }

//    val hashMap = HashMap<Int,Int>()
//    hashMap[nums[0]] = 0
//            for(i in 1 until nums.size) {
//        if(hashMap.containsKey(target - nums[i])) {
//            return intArrayOf(hashMap[target - nums[i]] as Int,i)
//        } else {
//            hashMap[nums[i]] = i
//        }
//    }
//        return intArrayOf(0)
}
