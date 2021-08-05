package com.zhewen.algorithmscollection.basicdata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

/**
 * 树与二叉树的算法实践
 */
public class Tree {

    public static void main(String[] args) {

    }

    /**
     * 二叉树
     */
    public class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * N叉树
     */
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };

    /**
     * 前序遍历
     * @param root 二叉树
     * @return 前序遍历列表
     */
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preorderTraversal(root,list);
        return list;
    }

    public static void preorderTraversal(TreeNode root,List<Integer> list) {
        if (root != null) {
            list.add(root.val);
            preorderTraversal(root.left,list);
            preorderTraversal(root.right,list);
        }
    }

    /**
     * 前序遍历，非递归迭代实现
     * @param root
     * @return
     */
    public static List<Integer> preorderTraversals(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while(!stack.isEmpty() || node != null) {
            while(node != null) {
                list.add(node.val);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }
        return list;
    }


    /**
     * 中序遍历
     * @param root
     * @return
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorderTraversal(root,list);
        return list;
    }

    public static void inorderTraversal(TreeNode root,List<Integer> list) {
        if (root != null) {
            inorderTraversal(root.left,list);
            list.add(root.val);
            inorderTraversal(root.right,list);
        }
    }

    /**
     * 中序遍历 非递归迭代实现
     * @param root
     * @return
     */
    public static List<Integer> inorderTraversals(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        TreeNode node = root;
        Stack<TreeNode> stack = new Stack<>();
        while(!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            list.add(node.val);
            node = root.right;
        }
        return list;
    }

    /**
     * 后序遍历
     * @param root
     * @return
     */
    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postorderTraversal(root,list);
        return list;
    }

    public static void postorderTraversal(TreeNode root, List<Integer> list) {
        if (root != null) {
            postorderTraversal(root.left,list);
            postorderTraversal(root.right,list);
            list.add(root.val);
        }
    }

    /**
     * todo
     * 后序遍历，非递归迭代方式实现
     * @param root
     */
    public static List<Integer> postorderTraversals(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        TreeNode prev = null;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            while(root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.right == null || root.right == prev){
                list.add(root.val);
                prev = root;
                root = null;
            } else {
                stack.push(root);
                root = root.right;
            }
        }
        return list;
    }

    /**
     * 二叉树层级遍历，使用广度优先算法，即用队列
     * 创建队列，判根节点是否为null
     * 将节点加入队列，然后获取当前队列长度
     * 循环根据队列长度取出队列节点，判断左右节点是否为空，非空加入队列
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            List<Integer> level = new ArrayList<>();
            int currentSize = queue.size();
            for (int i = 1; i <= currentSize; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            ret.add(level);
        }
        return ret;
    }

    /**
     * N叉树前序遍历,递归实现
     * @param root
     * @return
     */
    public static List<Integer> preorder(Node root) {
        List<Integer> list = new ArrayList<>();
        preorderNode(list,root);
        return list;
    }

    public static void preorderNode(List<Integer> list, Node root) {
        if (root != null) {
            list.add(root.val);
            if (root.children != null) {
                for (Node node: root.children) {
                    preorderNode(list,node);
                }
            }
        }
    }

    /**
     * N叉树前序遍历，非递归实现
     * @param root
     * @return
     */
    public static List<Integer> preOrderNodes(Node root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<Node> stack = new Stack<>();
        stack.add(root);
        while(!stack.isEmpty()) {
            Node node = stack.pop();
            list.add(node.val);
            Collections.reverse(node.children);
            for (Node item : node.children) {
                stack.push(item);
            }
        }
        return list;
    }

    /**
     * 对称二叉树，给定一个二叉树，判断它是对称的
     * 递归实现
     * @param root
     * @return
     */
    public static boolean isSymmetric(TreeNode root) {
        return check(root,root);
    }

    public static boolean check(TreeNode p,TreeNode q){
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && check(p.left,q.right) && check(p.right,q.left);
    }

    /**
     * 二叉树最大深度
     * 可以深度优先算法实现或广度优先算法实现，推荐深度，广度优先实现类似层级遍历
     * 这里设定根节点深度为1
     * @param root
     * @return
     */
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int leftDepth = maxDepth(root.left);
            int rightDepth = maxDepth(root.right);
            return Math.max(leftDepth,rightDepth) + 1;
        }
    }

    /**
     * N叉树的最大深度,实现原理与二叉树类似
     * @param root
     * @return
     */
    public static int maxDepth(Node root) {
        if (root == null) {
            return 0;
        } else if (root.children.isEmpty()) {
            return 1;
        } else {
            List<Integer> depth = new LinkedList<>();
            for (Node item:root.children) {
                depth.add(maxDepth(item));
            }
            return Collections.max(depth) + 1;
        }
    }

    /**
     * todo
     * 二叉树最小深度,这里深度优先算法实现
     * @param root
     * @return
     */
    public static int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int min_depth = Integer.MAX_VALUE;
        if (root.left != null) {
            min_depth = Math.min(minDepth(root.left),min_depth);
        }
        if (root.right != null) {
            min_depth = Math.min(minDepth(root.right),min_depth);
        }
        return min_depth + 1;

    }

    /**
     * 平衡二叉树判断
     * 即每个节点的左右两个子树高度差不超过1
     * @param root
     * @return
     */
    public static boolean isBalanced(TreeNode root){
        if (root == null) {
            return true;
        } else {
            return Math.abs(height(root.left) - height(root.right)) <= 1
                    && isBalanced(root.left) && isBalanced(root.right);
        }
    }

    public static int height(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return Math.max(height(root.left),height(root.right)) + 1;
        }
    }

    /**
     * todo
     * 二叉树所有路径
     * 递归深度搜索算法
     * @param root
     * @return
     */
    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> list = new ArrayList<>();
        constructPath(root,"",list);
        return list;
    }

    public static void constructPath(TreeNode root,String path,List<String> list) {
        if (root == null) {
            return;
        }
        StringBuilder stringBuilder = new StringBuilder(path);
        stringBuilder.append(root.val);
        if (root.left == null && root.right == null) {  //当前节点是叶子节点
            list.add(stringBuilder.toString());
        } else {
            stringBuilder.append("->");
            constructPath(root.left,stringBuilder.toString(),list);
            constructPath(root.right,stringBuilder.toString(),list);
        }
    }

    /**
     * 二叉树所有左叶子之和
     * todo
     * 使用深度或广度优先算法都可以实现，这里使用深度优先算法
     * @param root
     * @return
     */
    public static int sumOfLeftLeaves(TreeNode root){
        return root != null? dlf(root) : 0;
    }

    public static int dlf(TreeNode root) {
        int ans = 0;
        if (root.left != null) {
            ans += isLeafNode(root.left) ? root.left.val : dlf(root.left);
        }
        if (root.right != null && !isLeafNode(root.right)) {
            ans += dlf(root.right);
        }
        return ans;
    }

    public static boolean isLeafNode(TreeNode node) {
        return node.left == null && node.right == null;
    }

    /**
     * 找到树的左下角值，即最后一行最左边的值
     * @param root
     * @return
     */
    public static int findBottomLeftValue(TreeNode root){
        if (root == null) {
            return Integer.MIN_VALUE;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int res = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            int currentSize = queue.size();
            for (int i = 0; i< currentSize; i++) {
                TreeNode node = queue.poll();
                if (i == 0) {
                    res = node.val;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return res;
    }


    /**
     * 路径之和，即判断该树中是否存在根节点到叶子节点路径的节点值之和等于给定的目标值
     * 这里可用广度优先算法实现
     * @param root
     * @param targetSum
     * @return
     */
    public static boolean hasPathSum(TreeNode root, int targetSum){
        if (root == null) {
            return false;
        }
        Queue<TreeNode> queueNode = new LinkedList<>();
        Queue<Integer> queueVal = new LinkedList<>();
        queueNode.offer(root);
        queueVal.offer(root.val);
        while (!queueNode.isEmpty()){
            TreeNode now = queueNode.poll();
            int temp = queueVal.poll();
            if (now.left == null && now.right == null) {
                if (temp == targetSum) {
                    return true;
                }
                continue;
            }
            if (now.left != null) {
                queueNode.offer(now.left);
                queueVal.offer(temp + now.left.val);
            }
            if (now.right != null) {
                queueNode.offer(now.right);
                queueVal.offer(temp + now.right.val);
            }
        }
        return false;
    }

    /**
     * 路径之和,这里使用递归方法
     * @param root
     * @param targetSum
     * @return
     */
    public static boolean hasPathSums(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }
        return hasPathSums(root.left,targetSum-root.val) || hasPathSums(root.right,targetSum - root.val);
    }

    /**
     * 翻转二叉树
     * @param root
     * @return
     */
    public static TreeNode invertTree(TreeNode root){
        if (root == null) {
            return null;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    /**
     * 构造二叉树，从中序遍历和后序遍历序列构造二叉树
     * 递归实现，重点练习
     * @param inorder 中序遍历序列
     * @param postorder 后序遍历序列
     * @return
     */
    int[] postorder;
    int[] inorder;
    int post_index;
    Map<Integer,Integer> idx_map = new HashMap<Integer,Integer>();  //哈希映射
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.postorder = postorder;
        this.inorder = inorder;
        this.post_index = postorder.length - 1;
        int idx = 0;
        for (Integer val : inorder) {
            idx_map.put(val,idx++);
        }
        return helper(0,inorder.length - 1);
    }

    public TreeNode helper(int in_left,int in_right){
        if (in_left > in_right) {
            return null;
        }
        int root_val = postorder[post_index];
        TreeNode root = new TreeNode(root_val);
        int index = idx_map.get(root_val);  //将中序遍历分为左右两棵子树
        post_index--;
        root.right = helper(index + 1, in_right);
        root.left = helper(in_left,index - 1);
        return root;
    }


    /**
     * 构造二叉树，从前序和中序遍历序列
     * 这里递归实现
     * todo
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTrees(int[] preorder, int[] inorder) {
        int n = preorder.length;
        for (int i = 0; i < n; i++) {
            idx_map.put(inorder[i],i);
        }
        return helper(preorder,inorder,0, n-1,0,n-1);
    }

    public TreeNode helper(int[]preorder,int[]inorder,int preorder_left,int preorder_right,
                           int inorder_left,int inorder_right) {
        if (preorder_left > preorder_right) {
            return null;
        }
        int preorder_root_index = preorder_left;
        int inorder_root_index  = idx_map.get(preorder[preorder_root_index]);
        TreeNode root = new TreeNode(inorder[inorder_root_index]);
        int size_left_subtree = inorder_root_index - inorder_left;
        root.left = helper(preorder,inorder,preorder_left + 1,preorder_left + size_left_subtree,
                inorder_left,inorder_root_index - 1);
        root.right = helper(preorder,inorder,preorder_left + size_left_subtree + 1,preorder_right,
                inorder_root_index + 1, inorder_right);
        return root;
    }

    /**
     * 构建最大二叉树，即根节点为数组最大值
     * 递归实现
     * @param nums
     * @return
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return construct(nums,0,nums.length);
    }

    public TreeNode construct(int[]nums, int left, int right) {
        if (left == right) {
            return null;
        }
        int max_i = max(nums,left,right);
        TreeNode root = new TreeNode(nums[max_i]);
        root.left = construct(nums,left,max_i);
        root.right = construct(nums,max_i + 1, right);
        return root;
    }

    public int max(int[] nums, int l, int r) {
        int max_i = l;
        for (int i = l; i < r; i++) {
            if (nums[max_i] < nums[i]) {
                max_i = i;
            }
        }
        return max_i;
    }

    /**
     * 合并二叉树，如果两个节点重叠，那么将他们的值相加作为节点合并后的新值
     * @param root1
     * @param root2
     * @return
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2){
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        TreeNode root = new TreeNode(root1.val + root2.val);
        root.left = mergeTrees(root1.left,root2.right);
        root.right = mergeTrees(root1.right,root2.right);
        return root;
    }

    /**
     * 二叉搜索树中搜索给定值的节点
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBST(TreeNode root, int val){
        if (root == null) {
            return null;
        } else if (root.val == val) {
            return root;
        }
        return val < root.val ? searchBST(root.left,val): searchBST(root.right,val);
    }

    /**
     * 判断是否是一个有效二叉树
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root){
        return isValidBST(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode root,int low,int height){
        if (root == null) {
            return true;
        }
        if (root.val <= low || root.val >= height) {
            return false;
        }
        return isValidBST(root.left,low,root.val) && isValidBST(root.right,root.val,height);
    }

    /**
     * 计算二叉搜索树中任意两节点的绝对值的最小值
     * 中序遍历，相邻两个节点的差值最小
     * @param root
     * @return
     */
    int pre;
    int ans;
    public int getMinimumDifference(TreeNode root){
        ans = Integer.MAX_VALUE;
        pre = -1;
        dfs(root);
        return ans;
    }

    public void dfs(TreeNode root) {
        if (root == null) return;
        dfs(root.left);
        if (pre != -1) {
            ans = Math.min(ans, root.val - pre);
        }
        pre = root.val;
        dfs(root.right);
    }


    /**
     * 有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）
     * 中序遍历
     * @param root
     * @return
     */
    List<Integer> answer = new ArrayList<>();
    int base,count,maxCount;
    public int[] findMode(TreeNode root){
        findModeDfs(root);
        int[] mode = new int[answer.size()];
        for (int i = 0; i<answer.size(); i++) {
            mode[i] = answer.get(i);
        }
        return mode;
    }

    public void findModeDfs(TreeNode root) {
        if (root == null) {
            return;
        }
        findModeDfs(root.left);
        findModeUpdate(root.val);
        findModeDfs(root.right);
    }

    public void findModeUpdate(int x) {
        if (x == base) {
            ++count;
        } else {
            count = 1;
            base = x;
        }
        if (count == maxCount) {
            answer.add(base);
        }
        if (count > maxCount) {
            maxCount = count;
            answer.clear();
            answer.add(base);
        }
    }

    /**
     * 将二叉搜索树转换为累加树
     * @param root
     * @return
     */
    int sum = 0;
    public TreeNode convertBST(TreeNode root){
        if (root != null) {
            convertBST(root.right);
            sum += root.val;
            root.val = sum;
            convertBST(root.left);
        }
        return root;
    }

    /**
     * 二叉树最近的公共祖先
     * 递归实现
     * @param root
     * @param p
     * @param q
     * @return
     */
    private TreeNode commonNode = null;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q){
        dfs(root,p,q);
        return commonNode;
    }

    public boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return false;
        boolean lson = dfs(root.left,p,q);
        boolean rson = dfs(root.right,p,q);
        if ((lson && rson) ||((root.val == p.val || root.val == q.val) && (lson || rson))) {
            commonNode = root;
        }
        return lson || rson || (root.val == p.val || root.val == q.val);
    }

    /**
     * 二叉搜索树的最近公共祖先
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestors(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode ancestor = root;
        while (true) {
            if (p.val < ancestor.val && q.val < ancestor.val) {
                ancestor = ancestor.left;
            } else if (p.val > ancestor.val && q.val > ancestor.val) {
                ancestor = ancestor.right;
            } else {
                break;
            }
        }
        return ancestor;
    }
}
