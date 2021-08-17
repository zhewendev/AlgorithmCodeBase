package com.zhewen.algorithmscollection.slidewindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 滑动窗口算法框架
 */
public class SlideWindowFrame {
    /**
     * 滑动窗口最基本框架
     * @param s
     * @param t
     */
    public void slidingWindow(String s,String t) {
        Map<Character,Integer> need = new HashMap<>();
        Map<Character,Integer> window = new HashMap<>();
        for (int i = 0; i < t.length(); i ++) {
            need.put(t.charAt(i),i);
        }
        int left = 0;
        int right = 0;
        int valid = 0;
        while (right < s.length()) {
            // c 是将移入窗口的字符
            char c = s.charAt(right);
            //右移窗口
            right++;
            // 进行窗口内数据的一系列更新
            //...

            // 判断左侧窗口是否要收缩
//            while (window needs shrink) {
//                // d 是将移出窗口的字符
//                char d = s.charAt(left);
//                // 左移窗口
//                left++;
//                // 进行窗口内数据的一系列更新
//                //......
//            }
        }
    }

    /**
     * leetcode 76
     * 最小覆盖子串
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        Map<Character,Integer> need = new HashMap<>();
        Map<Character,Integer> window = new HashMap<>();
        for (int i = 0; i < t.length(); i ++) {
            need.put(t.charAt(i),need.getOrDefault(t.charAt(i),0) + 1);
        }
        int left = 0;
        int right = 0;
        int valid = 0;
        // 记录最小覆盖子串的起始索引及长度
        int start = 0;
        int len = Integer.MAX_VALUE;
        while (right < s.length()) {
            // c 是将移入窗口的字符
            char c = s.charAt(right);
            //右移窗口
            right++;
            // 进行窗口内数据的一系列更新
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c,0) + 1);
                if (Objects.equals(window.get(c), need.get(c))) {
                    valid++;
                }
            }
            //判断左侧窗口是否要收缩
            while (valid == need.size()) {
                // 在这里更新最小覆盖子串
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                // d 是将移出窗口的字符
                char d = s.charAt(left);
                // 左移窗口
                left++;
                // 进行窗口内数据的一系列更新
                if (need.containsKey(d)) {
                    if (Objects.equals(window.get(d),need.get(d))) {
                        valid--;
                    }
                    window.put(d,window.getOrDefault(d,0) - 1);
                }
            }
        }
        return len == Integer.MAX_VALUE? "":s.substring(start,start + len);
    }

    /**
     * leetcode 567
     * 字符串排列
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion(String s1, String s2) {
        Map<Character,Integer> need = new HashMap<>();
        Map<Character,Integer> window = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            need.put(s1.charAt(i),need.getOrDefault(s1.charAt(i),0) + 1);
        }
        int left = 0;
        int right = 0;
        int valid = 0;
        while (right < s2.length()) {
            char c = s2.charAt(right);
            right++;
            if (need.containsKey(c)) {
                window.put(c,window.getOrDefault(c,0) + 1);
                if (Objects.equals(window.get(c),need.get(c))) {
                    valid++;
                }
            }
            while (right - left >= s1.length()) {
                if (valid == need.size()) {
                    return true;
                }
                char d = s2.charAt(left);
                left++;
                if (need.containsKey(d)) {
                    if (Objects.equals(window.get(d),need.get(d))) valid--;
                    window.put(d,window.getOrDefault(d,0) - 1);
                }
            }
        }
        return false;
    }

    /**
     * leetcode 438
     * 找到字符串中所有字母异位词
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        Map<Character,Integer> need = new HashMap<>();
        Map<Character,Integer> window = new HashMap<>();
        for (int i = 0; i < p.length();i++) {
            need.put(p.charAt(i),need.getOrDefault(p.charAt(i),0) + 1);
        }
        int left = 0;
        int right = 0;
        int valid = 0;
        List<Integer> res = new ArrayList<>();
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            if (need.containsKey(c)) {
                window.put(c,window.getOrDefault(c,0) + 1);
                if (Objects.equals(window.get(c),need.get(c))) valid++;
            }
            while (right - left >= p.length()) {
                if (valid == need.size()) {
                    res.add(left);
                }
                char d = s.charAt(left);
                left++;
                if (need.containsKey(d)) {
                    if (Objects.equals(window.get(d),need.get(d))) {
                        valid--;
                    }
                    window.put(d,window.getOrDefault(d,0) - 1);
                }
            }
        }
        return res;
    }

    /**
     * leetcode 3
     * 无重复字符的最长子串
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        Map<Character,Integer> window = new HashMap<>();
        int left = 0;
        int right = 0;
        int res = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            window.put(c,window.getOrDefault(c,0) + 1);
            while (window.get(c) > 1) {
                char d = s.charAt(left);
                left++;
                window.put(d,window.getOrDefault(d,0) - 1);
            }
            res = Math.max(res,right - left);
        }
        return res;
    }
}
