package com.zhewen.algorithmscollection;

import java.util.Arrays;
import java.util.Scanner;

public class TempTest {
    public static void main(String[] args) {
        int[] array = {4, 2, 8, 9, 5, 7, 6, 1, 3, 10};
        int[] array1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int b = in.nextInt();

        int n = 4;  //大楼个数
        int total = 6;  //路径条数
        int[][] edges = new int[total][2];  //边
        int[] vals = new int[total];    //边的权重，即楼的距离


    }

    public int dijDistByMatrix(int n,int[][]edges,int[] vals, int start, int end) {
        int[][] cost = new int[n + 1][n + 1]; // 邻接矩阵
        int[] dis = new int[n + 1];  // 保留最短距离
        boolean[] vis = new boolean[n + 1];   // 最短距离的访问情况
        Arrays.fill(dis, Integer.MAX_VALUE);  // 最短距离初始化
        dis[start] = 0;  // start表示起点

        // 初始化
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    cost[i][j] = 0;
                }else {
                    cost[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        // 赋值
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            cost[u][v] = vals[i];
            cost[v][u] = vals[i];
        }

        // 计算
        while (true) {
            int v = -1;
            // 找到最小距离的下标
            for (int i = 1; i <= n; i++) {
                if (!vis[i] && (v == -1 || dis[i] < dis[v])) {
                    v = i;
                }
            }
            // 如果都被使用过，则跳出循环
            if (v == -1) break;
            vis[v] = true;  // 标记此最短距离已使用
            // 更新从最小距离下标出发到其他点的最短距离
            for (int u = 1; u <= n; u++) {
                if (dis[u] > dis[v] + cost[v][u]) {
                    dis[u] = dis[v] + cost[v][u];
                }
            }
        }

        // 返回
        return dis[end];
    }

}
