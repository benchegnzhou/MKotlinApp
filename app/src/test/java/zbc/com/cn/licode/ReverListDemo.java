package zbc.com.cn.licode;

import zbc.com.cn.MainActivity;

/**
 * 反转一个单链表。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 */
class ReverListDemo {

    public static void main(String[] args) {
        ListNode node0 = new ListNode(0);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node0.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;


        ListNode listNode = reverseList(node0);
        while (listNode != null) {
            System.out.println(listNode.getX());
            listNode = listNode.next;
        }
    }


    /**
     * 使用递归的方式
     *
     * @param head
     * @return
     */
    public static ListNode reverseList2(ListNode head) {
        //没有节点或者仅有一个节点没有必要翻转
        if (head == null || head.next == null) {
            return head;
        }
        int i = 0;
        while (head.next != null) {
            ListNode newNode = new ListNode(1);
            ListNode tempNode = head.next;
            newNode.next = head;
            head = tempNode;
            head.next = i == 0 ? null : null;


        }
        return null;
    }

    /**
     * 使用递归的方式
     *
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {
        //没有节点或者仅有一个节点没有必要翻转
        if (head == null || head.next == null) {
            return head;
        }
        //不是最后一节节点递归翻转
        ListNode newNode = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newNode;
    }
}
