package com.zhewen.algorithmscollection.dualpointer;

/**
 * 快慢指针典型问题
 */
public class FastAndSlowPointer {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * leetcode 141
     * 判断环形链表
     * @param head
     * @return
     */
    public boolean hasCycle (ListNode head){
        ListNode fast,slow;
        fast = slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    /**
     * leetcode 142
     * 链表有环，则找到环的第一个节点
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        ListNode fast,slow;
        fast = slow = head;
        while (true) {
            if (fast == null || fast.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) break;
        }
        slow = head;
        while (slow != fast) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * leetcode 876
     * 寻找链表的中间节点
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head) {
        ListNode fast,slow;
        fast = slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }


    /**
     * 剑指offer 22
     * 链表中倒数第k个节点
     * @param head
     * @param k
     * @return
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode fast,slow;
        fast = slow = head;
        while (k-- > 0) {
            if (fast != null) {
                fast = fast.next;
            } else {
                return null;
            }
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

}
