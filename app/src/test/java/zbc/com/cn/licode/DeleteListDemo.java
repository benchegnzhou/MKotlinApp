package zbc.com.cn.licode;


class DeleteListDemo {

    /**
     * 链表至少包含两个节点。
     * 链表中所有节点的值都是唯一的。
     * 给定的节点为非末尾节点并且一定是链表中的一个有效节点。
     * 不要从你的函数中返回任何结果。
     * <p>
     * Java
     *
     * @param args
     */
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
        deleteNode(node2);

        ListNode listNode = node0;
        while (listNode != null) {
            System.out.println(listNode.getX());
            listNode = listNode.next;
        }
    }

    public static void deleteNode(ListNode node) {
        //分析，如果删除指定节点，我们只需要把这个节点后面的节点赋值到这个节点的位置即可
        if (node != null) {
            ListNode nextNode = node.next;
            node.setX(nextNode.getX());

            node.setNext(nextNode == null ? null : nextNode.next);
        }
    }


}
