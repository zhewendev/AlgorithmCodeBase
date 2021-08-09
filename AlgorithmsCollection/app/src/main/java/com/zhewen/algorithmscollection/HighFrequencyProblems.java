package com.zhewen.algorithmscollection;

import java.util.Arrays;

/**
 * 一些经典高频问题
 */
public class HighFrequencyProblems {
    /**
     * leetcode 204
     * 寻找素数/计数质数
     */
    public int countPrimes(int n) {
        boolean[] isPrim = new boolean[n];
        Arrays.fill(isPrim, true);
        for (int i = 2; i * i < n; i++) {
            if (isPrim[i]) {
                for (int j = i * i; j < n; j += i) {
                    isPrim[j] = false;
                }
            }
        }
        int count = 0;
        for (int i = 2; i < n; i++)
            if (isPrim[i]) count++;
        return count;
    }

    /**
     * leetcode 372
     * 快速模幂
     *
     * @param a
     * @param b
     * @return
     */
    int base = 1337;

    public int superPow(int a, int[] b) {
        return superPow(a, b, b.length);
    }

    public int superPow(int a, int[] b, int length) {
        if (length == 0) return 1;
        int last = b[length - 1];
        int part1 = myPower(a, last);
        int part2 = myPower(superPow(a, b, length - 1), 10);
        return (part1 % base) * (part2 % base) % base;
    }

    public int myPower(int a, int b) {
        if (b == 0) return 1;
        a %= base;
        if (b % 2 == 1) {
            return (a * myPower(a, b - 1)) % base;
        } else {
            int sub = myPower(a, b / 2);
            return (sub * sub) % base;
        }
    }

    /**
     * leetcode 875
     * 爱吃香蕉的珂珂
     * @param piles
     * @param h
     * @return
     */
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = getMax(piles) + 1;
        while (left < right) {
            // 防⽌溢出
            int mid = left + (right - left) / 2;
            if (canFinish(piles, mid, h)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public boolean canFinish(int[] piles, int speed, int H) {
        int time = 0;
        for (int n : piles) {
            time += timeOf(n, speed);
        }
        return time <= H;
    }

    public int timeOf(int n, int speed) {
        return (n / speed) + ((n % speed > 0) ? 1 : 0);
    }

    public int getMax(int[] piles) {
        int max = 0;
        for (int n : piles)
            max = Math.max(n, max);
        return max;
    }

    /**
     * leetcode 1011
     * 在D天内送达包裹的能力
     * @param weights
     * @param days
     * @return
     */
    public int shipWithinDays(int[] weights, int days) {
        int left = getMax(weights); //载重可能的最小值
        int right = getSum(weights) + 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canFinishs(weights,days,mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public boolean canFinishs(int[] weights, int days,int cap) {
        int i = 0;
         for (int d = 0; d < days; d++) {
             int maxCap = cap;
             while ((maxCap -= weights[i]) >= 0) {
                 i++;
                 if (i == weights.length) {
                     return true;
                 }
             }
         }
         return false;
    }

    public int getSum(int[] weights) {
        int sum = 0;
        for (int weight : weights) {
            sum += weight;
        }
        return sum;
    }
}
