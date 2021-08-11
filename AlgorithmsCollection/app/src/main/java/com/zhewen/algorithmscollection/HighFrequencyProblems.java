package com.zhewen.algorithmscollection;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

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
     *
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
     *
     * @param weights
     * @param days
     * @return
     */
    public int shipWithinDays(int[] weights, int days) {
        int left = getMax(weights); //载重可能的最小值
        int right = getSum(weights) + 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canFinishs(weights, days, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public boolean canFinishs(int[] weights, int days, int cap) {
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

    /**
     * leetcode 42
     * 接雨水问题,双指针解决
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int length = height.length;
        if (length == 0) return 0;
        int left = 0;
        int right = length - 1;
        int ans = 0;
        int l_max = height[0];
        int r_max = height[length - 1];
        while (left <= right) {
            l_max = Math.max(l_max, height[left]);
            r_max = Math.max(r_max, height[right]);
            if (l_max < r_max) {
                ans += l_max - height[left];
                left++;
            } else {
                ans += r_max - height[right];
                right--;
            }
        }
        return ans;
    }

    /**
     * leetcode 26
     * 删除有序数组中的重复项，返回删除后数组的新长度
     * 通用技巧：将相关元素换到数组末尾
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        int slow = 0;
        int fast = 1;
        while (fast < n) {
            if (nums[fast] != nums[slow]) {
                slow++;
                nums[slow] = nums[fast];
            }
            fast++;
        }
        return slow + 1;
    }

    /**
     * leetcode 5
     * 最长回文子串
     * 核心思想：从中间向两侧扩散判断
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s == null || s.isEmpty()) return "";
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            String s1 = palindrome(s, i, i);
            String s2 = palindrome(s, i, i + 1 == s.length() ? i : i + 1);
            res = res.length() > s1.length() ? res : s1;
            res = res.length() > s2.length() ? res : s2;
        }
        return res;
    }

    public String palindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return s.substring(left + 1, right);
    }

    /**
     * leetcode 20
     * 有效的括号
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        int n = s.length();
        if (n % 2 == 1) {
            return false;
        }
        Map<Character, Character> pairs = new HashMap<Character, Character>() {
            {
                put(')','(');
                put('}','}');
                put(']','[');
            }
        };
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (pairs.containsKey(ch)){
                if (stack.isEmpty() || stack.peek() != pairs.get(ch)) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }

    /**
     * leetcode
     * 平衡括号字符串的最少插入次数
     * @param s
     * @return
     */
    public int minInsertionss(String s) {
        int res = 0;
        int need = 0;   //对应右括号
        for (int i = 0; i<s.length(); i++) {
            if (s.charAt(i) == '(') {
                need++;
            }
            if (s.charAt(i) == ')') {
                need--;
                if (need == -1) {
                    need = 0;
                    res ++;
                }
            }
        }
        return res + need;
    }

    /**
     * leetcode 1541
     * 平衡括号字符串的最少插入次数
     * @param s
     * @return
     */
    public int minInsertions(String s) {
        int res = 0;
        int need = 0;   //对应右括号
        for (int i = 0; i<s.length(); i++) {
            if (s.charAt(i) == '(') {
                need += 2;
                if (need % 2 == 1) {
                    res++;
                    need--;
                }
            }
            if (s.charAt(i) == ')') {
                need--;
                if (need == -1) {
                    need = 1;
                    res ++;
                }
            }
        }
        return res + need;
    }

}
