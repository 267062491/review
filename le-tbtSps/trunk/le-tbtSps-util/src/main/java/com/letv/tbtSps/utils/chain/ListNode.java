package com.letv.tbtSps.utils.chain;

/**
 * Created with IntelliJ IDEA.
 * User: yuguodong
 * Date: 17-6-25
 * Time: 下午6:18
 * To change this template use File | Settings | File Templates.
 */
public class ListNode {
    private Node first ;
    private int index ;

    public ListNode(){
        this.first = null ;
    }

    /**
     * 新增节点
     * 1、创建一个新节点
     * 2、把新节点的next设置为原来的头节点
     * 3、把新创建的节点设置为头节点
     * @param data
     */
    public void addFirstNode(int data){
        Node node = new Node(data);
        node.setNext(first) ;
        first = node;
    }

    /**
     * 在当前位置前插入新节点，编号从0开始
     * 1、首先把当前节点和前一个节点设置成一样的，都是头节点
     * 2、如果不是要操作的节点，则把当前节点赋值给前一个节点，当前节点赋值给当前节点的下一个节点，就是指针向下移动  ，并且把位置向下移动
     * 3、如果是要操作的节点则把新增的节点当做前一个节点的下一个节点，新增节点的下一个节点是当前节点
     * @param pos
     * @param data
     */
    public void add(int pos , int data){
        Node node = new Node(data);
        Node current = first ;
        Node pre = first ;
        while (pos != index){
            pre = current ;
            current = current.getNext();
            index ++ ;
        }
        node.setNext(current);
        pre.setNext(node);
        index = 0 ;
    }

    /**
     * 打印所有节点
     * 1、从头节点开始循环打印输出
     * 2、每次输出之后把指针指向下一个节点
     */
    public void printAll(){
        Node current = first ;
        while (current!=null){
            System.out.println(current.getData());
            current = current.getNext() ;
        }
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Node getFirst() {
        return first;
    }

    public void setFirst(Node first) {
        this.first = first;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode();
        listNode.addFirstNode(1);
        listNode.addFirstNode(2);
        listNode.addFirstNode(3);
        listNode.addFirstNode(4);
        listNode.add(3,5);
        listNode.printAll();
    }
}
