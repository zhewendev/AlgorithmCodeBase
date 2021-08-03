package com.zhewen.algorithmscollection.basicdata;

public class BasicDataTest {
    public static void main(String[] args) {
        int[] array = {4, 2, 8, 9, 5, 7, 6, 1, 3, 10};
        int[] array1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//        System.out.println("数组为" + Arrays.toString(array));
        int index = recursionBinarySearch(array1, 6, 0, array1.length - 1);
        System.out.println("索引为" + index);
    }


    /**
     * 二叉搜索树
     */
    public class Node{
        private int data;
        private Node leftChild;
        private Node rightChild;

        public Node(int data) {
            this.data = data;
        }

        /**
         * 查找节点
         */
        public Node find(int k,Node root) {
            Node current = root;
            while (current != null) {
                if (current.data > k){
                    current = current.leftChild;
                } else if (current.data < k) {
                    current = current.rightChild;
                } else {
                    return current;
                }
            }
            return null;
        }

        public boolean insert(int data,Node root){
            Node newNode = new Node(data);
            if (root == null) {
                root = newNode;
                return true;
            } else {
                Node current = root;
                Node parentNode = null;
                while (current != null) {
                    parentNode = current;
                    if (current.data > data) {
                        current = current.leftChild;
                        if (current == null) {
                            parentNode.leftChild = newNode;
                            return true;
                        }
                    } else {
                        current = current.rightChild;
                        if (current == null) {
                            parentNode.rightChild = newNode;
                            return true;
                        }
                    }
                }
            }
            return false;
        }
    }



    /**
     * 快排
     */
    public static void recQuickSort(int[]array,int left,int right){
        if (right > left) {
            int partition = partitionInt(array,left,right);
            recQuickSort(array,left,partition - 1);
            recQuickSort(array,partition + 1,right);
        }
    }

    private static int partitionInt(int[] array, int left, int right){
        int i = left;
        int j = right + 1;
        int pivot = array[left];    //选取头元素为基准元素
        while (true){
            while (i<right && array[++i] < pivot){
            }
            while (j > 0 && array[--j] > pivot){}
            if (i >= j) {
                break;
            } else {
                swap(array,i,j);
            }
        }
        swap(array,left,j);
        return j;

    }

    /**
     * 希尔排序
     */
    public static int[]shellSort(int[]array){
        int step = -1;
        int len = array.length;
        while(step <= len / 3) {
            step = step * 3 + 1;
        }

        while (step > 0) {
            //分别对每个增量间隔进行排序
            for (int i = step; i<len;i++){
                int temp = array[i];
                int j = i;
                while (j > step - 1 && temp <= array[j - step]){
                    array[j] = array[j - step];
                    j -= step;
                }
                array[j] = temp;
            }
            step = (step - 1) / 3;
        }
        return array;
    }

    /**
     * 求一个数的乘方
     */
    private static int pow(int x, int y) {
        if (x == 0 || x == 1) {
            return x;
        }
        if (y > 1) {
            int b = y / 2;
            int a = x * x;
            if (y % 2 == 1) {
                return pow(a,b) * x;
            } else {
                return pow(a,b);
            }
        } else if (y == 0) {
            return 1;
        } else {
            return x;
        }
    }

    /**
     * 归并排序
     * @return
     */
    public static int[] mergeSort(int[] c, int start, int last) {
        if (last > start) {
            int mid = (last - start) / 2 + start;
            mergeSort(c, start, mid);
            mergeSort(c, mid + 1, last);
            merge(c, start, mid, last);
        }
        return c;
    }


    public static void merge(int[] c, int start, int mid, int last) {
        int[] temp = new int[last - start + 1];// 定义临时数组
        int i = start;// 定义左边数组的下标
        int j = mid + 1;// 定义右边数组的下标
        int k = 0;
        while (i <= mid && j <= last) {
            if (c[i] < c[j]) {
                temp[k++] = c[i++];
            } else {
                temp[k++] = c[j++];
            }
        }
        // 把左边剩余数组元素移入新数组中
        while (i <= mid) {
            temp[k++] = c[i++];
        }
        // 把右边剩余数组元素移入到新数组中
        while (j <= last) {
            temp[k++] = c[j++];
        }

        // 把新数组中的数覆盖到c数组中
        if (temp.length >= 0) System.arraycopy(temp, 0, c, 0 + start, temp.length);
    }

    /**
     * 非递归归并排序
     */
    public static int[] reortSort(int[] a, int[] b) {
        int[] c = new int[a.length + b.length];
        int aNum = 0, bNum = 0, cNum = 0;
        while (aNum < a.length && bNum < b.length) {
            if (a[aNum] >= b[bNum]) {
                c[cNum] = b[bNum++];
            } else {
                c[cNum] = a[aNum++];
            }
        }
        while (aNum == a.length && bNum < b.length) {
            c[cNum++] = b[bNum++];
        }
        while (bNum == b.length && aNum < a.length) {
            c[cNum++] = a[aNum++];
        }
        return c;
    }

    /**
     * 汉诺塔问题
     */
    public static void move(int dish, String from, String temp, String to) {
        if (dish == 1) {
            System.out.println("将盘子" + dish + "从塔座" + from + "移动到目标塔座" + to);
        } else {
            move(dish - 1, from, to, temp);
            System.out.println("将盘子" + dish + "从塔座" + from + "移动到目标塔座" + to);
            move(dish - 1, temp, from, to);// B为初始塔座，C为目标塔座，A为中介塔座
        }
    }

    /**
     * 递归二分查找法
     *
     * @return
     */
    public static int recursionBinarySearch(int[] array, int k, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = (right - left) / 2 + left;
        if (array[mid] == k) {
            return mid;
        }
        if (array[mid] < k) {
            return recursionBinarySearch(array, k, mid + 1, right);
        }
        if (array[mid] > k) {
            return recursionBinarySearch(array, k, left, mid - 1);
        }
        return -1;
    }


    /**
     * 非递归二分查找
     *
     * @return 目标值索引
     */
    public static int binarySearch(int[] array, int k) {
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (array[mid] > k) {
                right = mid - 1;
            }
            if (array[mid] < k) {
                left = mid + 1;
            }
            if (array[mid] == k) {
                return mid;
            }
        }
        return -1;
    }


    /**
     * 阶乘
     *
     * @param n
     * @return
     */
    public static int getFactorial(int n) {
        if (n >= 0) {
            if (n == 1 || n == 0) {
                return 1;
            }
            return n * getFactorial(n - 1);
        }
        return -1;
    }


    /**
     * 单向链表实现
     */
    public static class SingleLinkedList {

        private int size;   //链表节点个数
        private Node head;  //头节点

        public SingleLinkedList() {
            size = 0;
            head = null;
        }

        private class Node {
            private Object data;
            private Node next;

            public Node(Object data) {
                this.data = data;
            }
        }

        /**
         * 链表头添加元素
         */
        public Object addHead(Object obj) {
            Node newHead = new Node(obj);
            if (size != 0) {
                newHead.next = head;
            }
            head = newHead;
            size++;
            return obj;
        }

        /**
         * 查找指定元素
         */
        public Object find(Object obj) {
            Node current = head;
            int tempSize = size;
            while (tempSize > 0) {
                if (obj.equals(current.data)) {
                    return current;
                } else {
                    current = current.next;
                }
                tempSize--;
            }
            return null;
        }


    }


    /**
     * 自定义构建队列
     */
    public class MyQueue {
        private Object[] queueArray;
        private int maxSize;
        private int front;  //前端
        private int rear;   //后端
        private int nItems; //实际数目

        public MyQueue(int maxSize) {
            this.maxSize = maxSize;
            this.queueArray = new Object[maxSize];
            front = 0;
            rear = -1;
            nItems = 0;
        }

        /**
         * 入列
         */
        public void insert(int value) {
            if (isFull()) {
                System.out.println("队列已满！！！");
            } else {
                // 如果队列尾部指向顶了，那么循环回来，执行队列的第一个元素
                if (rear == maxSize - 1) {
                    rear = -1;
                }
                queueArray[++rear] = value;
                nItems++;
            }
        }

        /**
         * 移除数据
         */
        public Object remove() {
            Object removeValue;
            if (!isEmpty()) {
                removeValue = queueArray[front];
                queueArray[front] = null;
                front++;
                if (front == maxSize) {
                    front = 0;
                }
                nItems--;
                return removeValue;
            }
            return null;
        }

        /**
         * 判断队列是否满了
         */
        public boolean isFull() {
            return nItems == maxSize;
        }

        /**
         * 判断队列是否为空
         */
        public boolean isEmpty() {
            return nItems == 0;
        }

        /**
         * 返回队列大小
         */
        public int getSize() {
            return nItems;
        }
    }

    /**
     * 自定义构建栈
     */
    public class MyStack {
        private int[] array;
        private int maxSize;
        private int top;

        public MyStack(int size) {
            array = new int[size];
            maxSize = size;
            top = -1;
        }

        /**
         * 入栈
         */
        public void push(int value) {
            if (top < maxSize - 1) {
                array[++top] = value;
            }
        }

        /**
         * 访问栈顶数据
         */
        public int peek() {
            return array[top];
        }

        /**
         * 判断栈是否为空
         */
        public boolean isEmpty() {
            return top == -1;
        }

        /**
         * 判断栈是否已满
         */
        public boolean isFull() {
            return top == maxSize - 1;
        }
    }


    /**
     * 直接插入
     *
     * @param array
     * @return
     */
    public static int[] insertSort(int[] array) {
        int j;
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];    //记录要插入的值
            j = i;
            while (j > 0 && temp < array[j - 1]) { // 从已经排序的序列最右边的开始比较，找到比其小的数
                array[j] = array[j - 1];
                j--;
            }
            array[j] = temp;
        }
        return array;
    }


    /**
     * 选择
     *
     * @param array
     * @return
     */
    public static int[] selectSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[min] > array[j]) {
                    min = j;
                }
            }
            if (i != min) {
                swap(array, i, min);
            }
        }
        return array;
    }


    public static void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }

    /**
     * 冒泡
     *
     * @param array
     * @return
     */
    public static int[] bubbleSort(int[] array) {
        for (int i = 1; i < array.length; i++) { //外层冒泡循环的次数
            boolean flag = true;//表示本次循环是否有数据交换
            for (int j = 0; j < array.length - i; j++) {
                if (array[j] < array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
        return array;
    }
}
