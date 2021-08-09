package com.zhewen.algorithmscollection.dynamicplanning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
            int low = nums[left];
            int height = nums[right];
            if (sum < target) {
                while (left < right && nums[left] == low) left++;
            } else if (sum > target) {
                while (left < right && nums[right] == height) right--;
            } else {
                res[0] = low;
                res[1] = height;
                break;
//                while (left < right && nums[left] == low) left++;
//                while (left < right && nums[right] == height) right--;
            }
        }
        return res;
    }

    /**
     * 两数之和，返回凑出target的两数索引下标
     * 就不能用排序法了，可用哈希表
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSums(int[] nums, int target) {
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (hashtable.containsKey(target - nums[i])) {
                return new int[]{hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i], i);
        }
        return new int[0];
    }

    /**
     * 找出三数之和为0，可以进阶为三数之和为target
     * 其实就是两数之和的进阶版，确定了一个数，
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums,int target) {
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<List<Integer>> lists = getTwoSum(nums, target - nums[i],i + 1);
            for(List<Integer> item : lists) {
                item.add(nums[i]);
                res.add(item);
            }
            while(i < n - 1 && nums[i] == nums[i+1]) i++;
        }
        return res;
    }

    public List<List<Integer>> getTwoSum(int[] nums, int target,int index) {
        int n = nums.length;
        int left = index + 1;
        int right = n - 1;
        List<List<Integer>> result = new ArrayList<>();
        while(left < right) {
            List<Integer> list = new ArrayList<>();
            int sum = nums[left] + nums[right];
            int low = nums[left];
            int height = nums[right];
            if (sum < target) {
                while (left < right && nums[left] == low) left++;
            } else if (sum > target) {
                while (left < right && nums[right] == height) right--;
            } else {
                list.add(low);
                list.add(height);
                result.add(list);
                while (left < right && nums[left] == low) left++;
                while (left < right && nums[right] == height) right--;
            }
        }
        return result;
    }

    /**
     * 4数之和
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<List<Integer>> list = getThreeSum(nums,target - nums[i],i + 1);
            for (List<Integer> item : list) {
                item.add(nums[i]);
                res.add(item);
            }
            while (i < n - 1 && nums[i] == nums[i + 1]) i++;
        }
        return res;
    }

    public List<List<Integer>> getThreeSum(int[] nums,int target,int index) {
        int n = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        for (int i = index + 1; i < n; i++) {
            List<List<Integer>> lists = getTwoSum(nums, target - nums[i],i + 1);
            for(List<Integer> item : lists) {
                item.add(nums[i]);
                res.add(item);
            }
            while(i < n - 1 && nums[i] == nums[i+1]) i++;
        }
        return res;
    }
}
