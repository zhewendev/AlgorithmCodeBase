package com.zhewen.algorithmscollection.dynamicplanning;

/**
 * 经典背包问题
 *试图将不同重量的数据项放到背包中，以使得背包最后达到指定的总重量。
 *
 * 假设想要让背包精确地承重20磅，并且有 5 个可以放入的数据项，它们的重量分别是 11 磅，8 磅，7 磅，6 磅，5 磅
 */
public class Knapsack {
    private int[]weights;   //可供选择的重量
    private boolean[] selects;  //记录是否被选择

    public Knapsack(int[] weights) {
        this.weights = weights;
        selects = new boolean[weights.length];
    }

    /**
     * 找出符合承担重量的组合
     * @param total 总重量
     * @param index 可供选择的重量下标索引
     */
    public void knapsack(int total,int index) {
        if (total < 0 || total > 0 && index >= weights.length) {
            return;// 没找到解决办法，直接返回
        }

    }
}
