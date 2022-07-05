package com.zhewen.algorithmscollection.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String firstLine = scanner.nextLine();  //第一行数据
        String[] aar = firstLine.split(" ");
        int n = Integer.parseInt(aar[0]);  //顶点个数
        int m = Integer.parseInt(aar[1]);
        ArrayList<Edge> edges = new ArrayList<>();
        Vertex[] vertexes = new Vertex[n];
        for (int i = 0; i < vertexes.length; i++) {
            vertexes[i] = new Vertex((char)(i + 97));
        }
        while (m > 0) {
            m--;
            String line = scanner.nextLine();
            String[] lineArray = line.split(" ");
            edges.add(new Edge(vertexes[Integer.parseInt(lineArray[0]) - 1],vertexes[Integer.parseInt(lineArray[1]) - 1],Integer.parseInt(lineArray[2])));
        }
        scanner.close();
        List<Edge> result1 = miniSpanningTree(edges); // 得到最小生成树
        int tempSum = printEdges(result1);
//        for (int i = 0; i< result1.size(); i++) {
//            if (i != 0) {
//                edges.add(result1.get(i - 1));
//            }
//            edges.remove(result1.get(i));
//            List<Edge> result2 = miniSpanningTree(edges);   //得到次小生成树
//            if (printEdges(result2) == tempSum) {
//                System.out.println(-1);
//                return;
//            }
//        }
//        System.out.println(tempSum);
        edges.remove(result1.get(0));
        for (int i = 0; i< edges.size(); i++) {
            System.out.print(edges.get(i).start.value);
            System.out.print(edges.get(i).end.value);
            System.out.print(edges.get(i).weight);
            System.out.println();
        }
        List<Edge> result2 = miniSpanningTree(edges);   //得到次小生成树
//        int tempSum = printEdges(result1);
        System.out.println(printEdges(result2));
        if (tempSum == printEdges(result2)) {
            System.out.println(-1);
        } else {
            System.out.println(tempSum);
        }
    }

    public static List<Edge> miniSpanningTree(List<Edge> edges) {
        ArrayList<Edge> result = new ArrayList<>();
        Collections.sort(edges); // 根据边权重从小到大排序
        for (Edge edge : edges) {
            Vertex u = edge.getStart();
            Vertex v = edge.getEnd();
            // 如果 u 和 v 已经在同一颗树里则跳过
            if (u.getRoot() == v.getRoot()) {
                continue;
            }
            result.add(edge);
            // 将 u 和 v 放在同一颗树里
            // 合并两个树最直接的办法就是使用一个新的根结点，然后连接两个子树
            TreeNode newRoot = new TreeNode();
            u.setRoot(newRoot);
            v.setRoot(newRoot);
        }
        return result;
    }



    public static int printEdges(List<Edge> edges) {
        int result = 0;
        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            result += edge.getWeight();
        }
        return result;
    }

    public static class Vertex extends TreeNode {

        private char value; // 顶点的值

        public Vertex(char value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public TreeNode getRoot() {
            TreeNode root = this;
            while (root.getParent() != null) {
                root = root.getParent();
            }
            return root;
        }

        public void setRoot(TreeNode treeNode) {
            getRoot().setParent(treeNode);
        }

    }

    public static class TreeNode {

        protected TreeNode parent;

        public TreeNode getParent() {
            return parent;
        }

        public void setParent(TreeNode parent) {
            this.parent = parent;
        }

    }

    public static class Edge implements Comparable<Edge> {

        private Vertex start;
        private Vertex end;
        private int weight; // 权重

        public Edge(Vertex start, Vertex end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        public Vertex getStart() {
            return start;
        }

        public Vertex getEnd() {
            return end;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public int compareTo(Edge other) {
            return this.weight - other.weight;
        }
    }
}
