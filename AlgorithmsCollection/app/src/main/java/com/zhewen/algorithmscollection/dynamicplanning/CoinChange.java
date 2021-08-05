package com.zhewen.algorithmscollection.dynamicplanning;

import java.util.Arrays;

/**
 * 零钱兑换问题
 */
public class CoinChange {
    /**
     * 计算并返回可以凑成总金额所需的 最少的硬币个数
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 0; i<= amount; i++) {
            for (int coin : coins) {
                if (i - coin < 0) continue;
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        return (dp[amount] == amount + 1) ? -1: dp[amount];
    }
}
