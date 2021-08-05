package com.zhewen.algorithmscollection.dynamicplanning;

/**
 * 股票买卖问题
 */
public class StockBuyOrSell {
    /**
     * 只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。
     * 即只能进行一次交易
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[] dp_i_0 = new int[n];
        int[] dp_i_1 = new int[n];
        dp_i_0[0] = 0;
        dp_i_1[0] = -prices[0];
        for (int i = 1; i< n; i++) {
            dp_i_0[i] = Math.max(dp_i_0[i - 1],dp_i_1[i - 1] + prices[i]);
            dp_i_1[i] = Math.max(dp_i_1[i - 1],- prices[i]);
        }
        return dp_i_0[n - 1];
        //可以如下进一步优化
//        int dp_i_0 = 0;
//        int dp_i_1 = -prices[0];
//        for (int i = 1; i < n; i++) {
//            dp_i_0 = Math.max(dp_i_0,dp_i_1 + prices[i]);
//            dp_i_1 = Math.max(dp_i_1,-prices[i]);
//        }
//        return dp_i_0;
    }

    /**
     * 股票买卖，不限制交易次数
     * @param prices
     * @return
     */
    public int maxProfits(int[] prices) {
        int n = prices.length;
        int dp_i_0 = 0;
        int dp_i_1 = -prices[0];
        for (int i = 1; i < n; i++) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0,dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1,temp - prices[i]);
        }
        return dp_i_0;
    }

    /**
     * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)
     * @param prices
     * @return
     */
    public int maxProfitCold(int[] prices) {
        int n = prices.length;
        int dp_i_0 = 0;
        int dp_i_1 = -prices[0];
        int dp_pre = 0;
        for (int i = 1;i < n; i++) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0,dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, dp_pre - prices[i]);
            dp_pre = temp;
        }
        return dp_i_0;
    }

    /**
     * 买卖股票时存在手续费
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int dp_i_0 = 0;
        int dp_i_1 = -prices[0];
        for (int i = 1; i < n; i++) {
            dp_i_0 = Math.max(dp_i_0,dp_i_1 + prices[i] - fee);
            dp_i_1 = Math.max(dp_i_1,dp_i_0 - prices[i]);
        }
        return dp_i_0;
    }

    /**
     * 股票买卖，最多交易两次
     * dp[i][2][0] = max(dp[i-1][2][0], dp[i-1][2][1] + prices[i])
     * dp[i][2][1] = max(dp[i-1][2][1], dp[i-1][1][0] - prices[i])
     * dp[i][1][0] = max(dp[i-1][1][0], dp[i-1][1][1] + prices[i])
     * dp[i][1][1] = max(dp[i-1][1][1], -prices[i])
     * @param prices
     * @return
     */
    public int maxProfitMostTwo(int[] prices) {
        int dp_i10 = 0, dp_i11 = Integer.MIN_VALUE;
        int dp_i20 = 0, dp_i21 = Integer.MIN_VALUE;
        for (int price : prices) {
            dp_i20 = Math.max(dp_i20, dp_i21 + price);
            dp_i21 = Math.max(dp_i21, dp_i10 - price);
            dp_i10 = Math.max(dp_i10, dp_i11 + price);
            dp_i11 = Math.max(dp_i11, -price);
        }
        return dp_i20;
    }

    /**
     * 股票买卖，最多交易k次
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (k > n / 2) {
            return maxProfits(prices);  //相当于交易次数无限制
        }
        int[][][]
    }
}
