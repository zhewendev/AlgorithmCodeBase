package com.zhewen.algorithmscollection.dynamicplanning;

import com.zhewen.algorithmscollection.basicdata.Tree;

/**
 * 打家劫舍系列问题
 */
public class HackUpTheHouse {
    /**
     *不能抢劫相邻的房子
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] dp = new int[n + 2];
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = Math.max(dp[i + 1],nums[i] + dp[i + 2]);
        }
        return dp[0];
    }

    /**
     * 房子不是一排，而是一圈
     * @param nums
     * @return
     */
    public int robs(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        return Math.max(robRange(nums,0,n-2),robRange(nums,1,n-1));
    }

    public int robRange(int[] nums, int start, int end) {
        int n = nums.length;
        int dp_i_1 = 0; int dp_i_2 = 0;
        int dp_i = 0;
        for (int i = end; i >= start; i--) {
            dp_i = Math.max(dp_i_1,nums[i] + dp_i_2);
            dp_i_2 = dp_i_1;
            dp_i_1 = dp_i;
        }
        return dp_i;

    }

    /**
     * 房子是一棵二叉树，相连房子不能同时被抢劫
     * @param root
     * @return
     */
    public int rob(Tree.TreeNode root) {
        int[] res = dp(root);
        return Math.max(res[0],res[1]);
    }

    public int[] dp(Tree.TreeNode root) {
        if (root == null) {
            return new int[]{0,0};
        }
        //arr[0] 表示不抢 root 的话，得到的最大钱数
        //arr[1] 表示抢 root 的话，得到的最大钱数
        int[] left = dp(root.left);
        int[] right = dp(root.right);
        int rob = root.val + left[0] + right[0];
        int not_rob = Math.max(left[0],left[1]) + Math.max(right[0],right[1]);
        return new int[]{not_rob,rob};
    }
}
